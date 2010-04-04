package webmenu.data;

import java.util.Date;
import webmenu.model.*;

public interface DataStore
{
    /// Insert a new record or update an existing one
    void updateOneDayMenu(String restaurant, OneDayMenu menu);
    
    /// Fetch a menu
    /// @return null if not found
    OneDayMenu getOneDayMenu(String restaurant, Date day);

    /// Load GlobalData from the store.
    GlobalData loadGlobalData();

    /// Save GlobalData to the store.
    void saveGlobalData(GlobalData data);
}
