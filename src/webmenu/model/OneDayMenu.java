package webmenu.model;
import java.util.*;

public class OneDayMenu
{
    private Calendar day;
    private SoupItem[] soupItems;
    private MenuItem[] menuItems;

    public Calendar getDay()
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


    public OneDayMenu(Calendar day, SoupItem[] soups, MenuItem[] meals)
    {
        this.day = day;
        this.soupItems = soups;
        this.menuItems = meals;
    }
}
