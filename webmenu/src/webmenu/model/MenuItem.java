package webmenu.model;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class MenuItem
{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     *  Short title, indicating menu name.
     *  Example: "Menu 1", "Česká kuchyně", etc.
     */
    @Persistent
    private String name;

    /**
     * Description of the meal.
     * Example: "100g Kuřecí nudličky na kari se smetanou, špagety, sýr"
     */
    @Persistent
    private String meal;

    /* not used yet
     * Price for menu without soup - 0 means not available.
     *
    public int price1;
    */

    /**
     * Price for menu with soup - 0 means not available.
     */
    @Persistent
    private int price;

    public String getName() { return name; }
    public String getMeal() { return meal; }
    public int getPrice() { return price; }

    public MenuItem(String name, String meal, int price)
    {
        this.name = name;
        this.meal = meal;
        this.price = price;
    }

    public MenuItem(MenuItem other)
    {
        this (other.name, other.meal, other.price);
    }
}
