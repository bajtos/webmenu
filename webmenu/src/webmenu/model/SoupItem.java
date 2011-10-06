package webmenu.model;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class SoupItem
{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     *  Short title.
     *  Example: "Polévka".
     */
    @Persistent
    private String name;

    /**
     * Description of the meal.
     * Example: "Čočková s párkem"
     */
    @Persistent
    private String meal;

    /* not used yet
     * Price for soup ordered standalone - 0 means not available.
     *
    @Persistent
    private int price;
     */

    public String getName() { return name; }
    public String getMeal() { return meal; }

    public SoupItem(String name, String meal)
    {
        this.name = name;
        this.meal = meal;
    }

    public SoupItem(SoupItem other)
    {
        this(other.name, other.meal);
    }
}
