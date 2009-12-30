package webmenu; 
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import webmenu.crawler.*;
import webmenu.data.Restaurants;

public class UpdateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
        Crawler crawler;

        if (path.equals("/" + Restaurants.MAM_HLAD_HK)) {
            crawler = new MamHladHkCrawler();
        } else {
            log.warning("Unknown restaurant '" + path + "'.");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            crawler.update();
            resp.setContentType("text/plain");
            resp.getWriter().println("OK");
        } catch (CrawlException e) {
            log.log(Level.SEVERE, "Update failed", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}
}
