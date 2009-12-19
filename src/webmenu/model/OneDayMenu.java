package webmenu.model;
import java.util.*;

public class OneDayMenu
{
    private Date day;
    private List<SoupItem> soupItems;
    private List<MenuItem> menuItems;

    public Date getDay()
    {
        return day;
    }

    public Collection<SoupItem> getSoupItems()
    {
        return soupItems;
    }

    public Collection<MenuItem> getMenuItems()
    {
        return menuItems;
    }


    OneDayMenu(Date day)
    {
        this.day = day;
    }

    public void addItem(SoupItem item)
    {
        soupItems.add(item);
    }

    public void addItem(MenuItem item)
    {
        menuItems.add(item);
    }
}
