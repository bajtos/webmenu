package webmenu;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class MainServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
      resp.setStatus(HttpServletResponse.SC_GONE);
      resp.setContentType("text/plain");
      resp.getWriter().write("Stranky jsou pryc. Pokud mate zajem prevzit kod a/nebo domenu, tak me kontaktujte na < miro.bajtos[zavinac]gmail.com >.");
	}
}
