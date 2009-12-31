package webmenu;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.Logger;
import java.util.*;
import org.apache.commons.lang.StringUtils;

import webmenu.data.*;

/// A temporary servlet implementing all functionality.
/// TODO: refactor it to MVC.

public class MainServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getName());

    protected OneDayMenuStore createMenuStore()
    {
        return new GoogleOneDayMenuStore();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String path = req.getPathInfo();
		log.fine("Request path: " + path);
      if (path == null) 
         path = "/";

      String[] components = StringUtils.split(path, '/');
      if (components.length < 1) {
			doRedirectToHradec(req, resp);
         return;
      }

      if (components[0].equals("hradec-kralove"))
      {
         if (components.length < 2) {
            doRedirectToHradecDelivery(req, resp);
            return;
         }

         if (components[1].equals("rozvoz"))
         {
            try {
                Calendar day = null;
                if (components.length == 2)
                   day = Calendar.getInstance();
                else if (components.length == 5)
                   day = new GregorianCalendar(Integer.parseInt(components[2]), Integer.parseInt(components[3])-1, Integer.parseInt(components[4]));
                if (day != null) {
                   doList(req, resp, day);
                   return;
                }
            } catch (Exception e) {
                throw new ServletException("Unhandled exception", e);
            }

         }
      }

      log.warning("Path '" + path + "' was not found.");
      log.fine("Components: " + StringUtils.join(components, '/'));
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	void doRedirectToHradec(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.fine("Redirected to hradec-kralove");
		resp.sendRedirect("/menu/hradec-kralove");
	}

	void doRedirectToHradecDelivery(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		log.fine("Redirected to hradec-kralove/rozvoz");
		resp.sendRedirect("/menu/hradec-kralove/rozvoz");
	}

   void doList(HttpServletRequest req, HttpServletResponse resp, Calendar day) throws ServletException, IOException {
      log.fine("serving delivery view");

      Date today = day.getTime();

      req.setAttribute("webmenu:location", "Hradec Králové");
      req.setAttribute("webmenu:today", today);

      OneDayMenuStore store = createMenuStore();

      String[] restaurants = new String[] {
          Restaurants.MAM_HLAD_HK 
      };
      for (String r : restaurants)
          req.setAttribute(Restaurants.getAttributeName(r), store.getOneDayMenu(r, today));

      view(req, resp, "delivery");
   }

    void view(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/" + view + ".jsp");
        dispatcher.forward(request, response);
    }

}
