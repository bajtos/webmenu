package webmenu.model;
import java.util.*;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class OneDayMenu
{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private Calendar day;

    @Persistent
    private List<SoupItem> soupItems;

    @Persistent
    private List<MenuItem> menuItems;

    public Calendar getDay()
    {
        return day;
    }

    public List<SoupItem> getSoupItems()
    {
        return soupItems;
    }

    public List<MenuItem> getMenuItems()
    {
        return menuItems;
    }

    public void setKey(Key key)
    {
        this.key = key;
    }

    public OneDayMenu(Calendar day, List<SoupItem> soups, List<MenuItem> meals)
    {
        this.day = day;
        this.soupItems = soups;
        this.menuItems = meals;
    }
}
