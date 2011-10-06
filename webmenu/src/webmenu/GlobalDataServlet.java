package webmenu;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

import webmenu.data.*;
import webmenu.model.GlobalData;

public class GlobalDataServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(GlobalDataServlet.class.getName());

    private DataStore store = new GoogleDataStore();
    private final static String requestAttributeName = GlobalData.class.getName();

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        request.setAttribute(GlobalData.class.getName(), store.loadGlobalData());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view/global-data.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        GlobalData data = store.loadGlobalData();
        data.setWarningText(request.getParameter("WarningText"));
        store.saveGlobalData(data);
  
        doGet(request, response);
    }
}
