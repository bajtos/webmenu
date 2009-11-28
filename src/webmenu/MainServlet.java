package webmenu;

import java.io.IOException;
import javax.servlet.http.*;
//import org.apache.log4j.Logger;

/// A temporary servlet implementing all functionality.
/// TODO: refactor it to MVC.

/// TODO: logging - we need to solve add log4j jar in build.xml
/// see http://code.google.com/appengine/docs/java/tools/ant.html

public class MainServlet extends HttpServlet {
//	private static org.apache.log4j.Logger log = Logger.getLogger(MainServlet.class);

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String path = req.getPathInfo();
//		log.info("Request path: " + path);
		System.out.println("Request path: " + path + " URI: " + req.getRequestURI());
		if (path == null || "/".equals(path))
			doRedirectToHradec(req, resp);
		else if ("/hradec-kralove".equals(path))
			doRedirectToHradecDelivery(req, resp);
		else if ("/hradec-kralove/rozvoz".equals(path))
			doList(req, resp);
		else 
		{
			System.out.println("Path '" + path + "' was not found.");
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	void doRedirectToHradec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Redirected to hradec-kralove");
		resp.sendRedirect(resp.encodeRedirectURL("hradec-kralove"));
	}

	void doRedirectToHradecDelivery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Redirected to hradec-kralove/rozvoz");
		resp.sendRedirect(resp.encodeRedirectURL("hradec-kralove/rozvoz"));
	}

	void doList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Rozvoz jidla.");
	}
}
