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
		else if (path.equals("/hradec-kralove"))
			doRedirectToHradecDelivery(req, resp);
		else if (path.equals("/hradec-kralove/rozvoz"))
			doList(req, resp);
		else 
		{
			log.warning("Path '" + path + "' was not found.");
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	void doRedirectToHradec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.fine("Redirected to hradec-kralove");
		resp.sendRedirect("/menu/hradec-kralove");
	}

	void doRedirectToHradecDelivery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.fine("Redirected to hradec-kralove/rozvoz");
		resp.sendRedirect("/menu/hradec-kralove/rozvoz");
	}

   void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      log.fine("serving delivery view");
      req.setAttribute("location", "Hradec Králové");
      view(req, resp, "delivery");
   }

    void view(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/" + view + ".jsp");
        dispatcher.forward(request, response);
    }

}
