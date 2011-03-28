package webmenu;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.Logger;
import java.util.*;
import org.apache.commons.lang.StringUtils;

import webmenu.data.*;
import webmenu.model.*;
import webmenu.viewmodel.*;

/// A temporary servlet implementing all functionality.
/// TODO: refactor it to MVC.

public class MainServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getName());

    protected DataStore createMenuStore()
    {
        return new GoogleDataStore();
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
                if (components.length == 2)
                   doList(req, resp, null);
                else if (components.length == 3) {
                    String day = components[2];
                    if (day.equals("pondeli"))
                        doList(req, resp, getDate(Calendar.MONDAY));
                    else if (day.equals("utery"))
                        doList(req, resp, getDate(Calendar.TUESDAY));
                    else if (day.equals("streda"))
                        doList(req, resp, getDate(Calendar.WEDNESDAY));
                    else if (day.equals("ctvrtek"))
                        doList(req, resp, getDate(Calendar.THURSDAY));
                    else if (day.equals("patek"))
                        doList(req, resp, getDate(Calendar.FRIDAY));
                    else doRedirectToHradecDelivery(req, resp);
                } else if (components.length == 6 && components[2] == "archived") {
                   Calendar day = new GregorianCalendar(Integer.parseInt(components[3]), Integer.parseInt(components[4])-1, Integer.parseInt(components[5]));
                   doList(req, resp, day);
                } else if (components.length == 5)
                   doRedirectToHradecDelivery(req, resp); // old style of URL
            } catch (Exception e) {
                throw new ServletException("Unhandled exception", e);
            }
         }
      }

      log.warning("Path '" + path + "' was not found.");
      log.fine("Components: " + StringUtils.join(components, '/'));
      resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

    Calendar getDate(int dayOfWeek)
    {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        return date;
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

      DeliveryViewModel model = new DeliveryViewModel(req.getServletPath() + "/hradec-kralove/rozvoz", "Hradec Králové", day);

      DataStore store = createMenuStore();

      for (String r : Restaurants.getKeys())
      {
          OneDayMenu menu = store.getOneDayMenu(r, model.getDate().getTime());
          Restaurant restaurant = Restaurants.getRestaurant(r);
          model.setMenu(r, new DayMenuViewModel(menu, restaurant));
      }

      model.setWarningText(store.loadGlobalData().getWarningText());

      model.set(req);
      view(req, resp, "delivery");
   }

    void view(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/" + view + ".jsp");
        dispatcher.forward(request, response);
    }

}
