package webmenu;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.Logger;

/// A temporary servlet implementing all functionality.
/// TODO: refactor it to MVC.

public class MainServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		log.fine("Request path: " + path);
		if (path == null || "/".equals(path))
			doRedirectToHradec(req, resp);
		else if ("/hradec-kralove".equals(path))
			doRedirectToHradecDelivery(req, resp);
		else if ("/hradec-kralove/rozvoz".equals(path))
			doList(req, resp);
		else 
		{
			log.warning("Path '" + path + "' was not found.");
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	void doRedirectToHradec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("Redirected to hradec-kralove");
		resp.sendRedirect(resp.encodeRedirectURL("hradec-kralove"));
	}

	void doRedirectToHradecDelivery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.info("Redirected to hradec-kralove/rozvoz");
		resp.sendRedirect(resp.encodeRedirectURL("hradec-kralove/rozvoz"));
	}

   void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setAttribute("location", "Hradec Králové");
      view(req, resp, "DeliveryView");
   }

    void view(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getNamedDispatcher(view);
        dispatcher.forward(request, response);
    }

}
