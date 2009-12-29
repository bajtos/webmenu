package webmenu.model;

public class MenuItem
{
    /**
     *  Short title, indicating menu name.
     *  Example: "Menu 1", "Česká kuchyně", etc.
     */
    public String name;

    /**
     * Description of the meal.
     * Example: "100g Kuřecí nudličky na kari se smetanou, špagety, sýr"
     */
    public String meal;

    /* not used yet
     * Price for menu without soup - 0 means not available.
     *
    public int price1;
    */

    /**
     * Price for menu with soup - 0 means not available.
     */
    public int price;

    public MenuItem(String name, String meal, int price)
    {
        this.name = name;
        this.meal = meal;
        this.price = price;
    }
}
