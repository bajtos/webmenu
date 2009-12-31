package webmenu.data;

import java.util.Date;
import webmenu.model.OneDayMenu;

public interface OneDayMenuStore
{
    /// Insert a new record or update an existing one
    void updateOneDayMenu(String restaurant, OneDayMenu menu);
    
    /// Fetch a menu
    /// @return null if not found
    OneDayMenu getOneDayMenu(String restaurant, Date day);
}