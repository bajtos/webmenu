package webmenu.model;

public class SoupItem
{
    /**
     *  Short title.
     *  Example: "Polévka".
     */
    public String name;

    /**
     * Description of the meal.
     * Example: "Čočková s párkem"
     */
    public String meal;

    /**
     * Price for soup ordered standalone - 0 means not available.
     */
    public int price;
}
