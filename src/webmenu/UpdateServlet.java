package webmenu; 

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.lang.exception.ExceptionUtils;

import webmenu.crawler.*;
import webmenu.data.Restaurants;

public class UpdateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
        Crawler crawler;

        if (("/" + Restaurants.MAM_HLAD_HK).equals(path)) {
            crawler = new MamHladHkCrawler();
        } else if (("/" + Restaurants.SPORT_CAFE_HK).equals(path)) {
            crawler = new SportCafeCrawler();
        } else if ("/send-test-email".equals(path)) {
            sendEmailNotification("test email\n" + ExceptionUtils.getFullStackTrace(new Exception("test")));
            resp.setContentType("text/plain");
            resp.getWriter().println("sent");
            return;
        } else {
            log.warning("Unknown restaurant '" + path + "'.");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            String urlString = req.getParameter("url");
            URL url = urlString == null || urlString.isEmpty() ? null : new URL(urlString);

            crawler.update(url);
            resp.setContentType("text/plain");
            resp.getWriter().println("OK");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Update failed", e);
            sendEmailNotification("Update failed:\n" + ExceptionUtils.getFullStackTrace(e));
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}

    public void sendEmailNotification(String bodyText)
    {
        try {
            Session session = Session.getDefaultInstance(new Properties(), null);
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("mirco.sk@gmail.com", "chci-obed update"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("mirco.sk@gmail.com"));
            msg.setSubject("chci-obed update problem");
            msg.setText(bodyText);

            Transport.send(msg);
        } catch (AddressException e) {
            log.log(Level.SEVERE, "Cannot send e-mail", e);
        } catch (MessagingException e) {
            log.log(Level.SEVERE, "Cannot send e-mail", e);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Cannot send e-mail", e);
        }
    }
}
