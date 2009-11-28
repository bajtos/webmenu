package webmenu;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.Logger;

public class UpdateServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("TODO - update");
	}
}
