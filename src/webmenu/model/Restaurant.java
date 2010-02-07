package webmenu.model;

public class Restaurant {
    private String name;
    private String phone;
    private String menuLink;
    private String contactLink;
    private String[] disclaimers;

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getMenuLink() { return menuLink; }
    public String getContactLink() { return contactLink; }
    public String[] getDisclaimers() { return disclaimers; }

    public Restaurant(String name, String phone, String menuLink, String contactLink, String[] disclaimers)
    {
        this.name = name;
        this.phone = phone;
        this.menuLink = menuLink;
        this.contactLink = contactLink;
        this.disclaimers = disclaimers;
    }
}

