package webmenu.model;
import java.util.*;

public class OneDayMenu
{
    private Date day;
    private SoupItem[] soupItems;
    private MenuItem[] menuItems;

    public Date getDay()
    {
        return day;
    }

    public SoupItem[] getSoupItems()
    {
        return soupItems;
    }

    public MenuItem[] getMenuItems()
    {
        return menuItems;
    }


    OneDayMenu(Date day, SoupItem[] soups, MenuItem[] meals)
    {
        this.day = day;
        this.soupItems = soups;
        this.menuItems = meals;
    }
}
