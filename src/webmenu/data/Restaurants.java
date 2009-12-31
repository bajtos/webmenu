package webmenu.data;

/// List of restaurant keys into data-store.
public class Restaurants
{
    public final static String MAM_HLAD_HK = "mam-hlad-hk";

    public static String getAttributeName(String restaurant)
    {
       return "webmenu:" + restaurant;
    }
};

