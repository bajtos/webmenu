package webmenu.data;

import java.util.Calendar;
import java.util.logging.Logger;

import webmenu.model.OneDayMenu;

/// Implementation of OneDayMenuStore using Google data-store API
public class GoogleOneDayMenuStore implements OneDayMenuStore
{
    private static final Logger log = Logger.getLogger(GoogleOneDayMenuStore.class.getName());

    public void updateOneDayMenu(String restaurant, OneDayMenu menu)
    {
        // TODO
        log.severe("Not implemented yet.");
    }
    
    public OneDayMenu getOneDayMenu(String restaurant, Calendar day)
    {
        // TODO
        log.severe("Not implemented yet.");
        return null;
    }
}
