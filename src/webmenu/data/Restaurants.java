package webmenu.data;

import webmenu.model.Restaurant;

/// List of restaurant keys into data-store.
public class Restaurants
{
    public final static String MAM_HLAD_HK = "mam-hlad-hk";
    public final static String SPORT_CAFE_HK = "sport-cafe-hk";
    public final static String CAFE_POPULAR_HK = "cafe-popular-hk";

    private final static String STANDARD_DISCLAIMER = "Uvedené ceny jsou pouze orientační, doporučujeme je zkontrolovat před objednáním.";
    private final static String TWOMEALS_DISCLAIMER = "Pro dovezení jídla je nutné objednat alespoň dvě porce (menu).";

    public static String[] getKeys() {
      return new String[] { MAM_HLAD_HK, SPORT_CAFE_HK, CAFE_POPULAR_HK };
    }

    public static Restaurant getRestaurant(String key) {
        if (key.equals(Restaurants.MAM_HLAD_HK))
            return new Restaurant(
                    "MámHladvHK",
                    "773 452 345",
                    "http://www.mamhladvhk.cz/tydenni-menu.php",
                    "http://www.mamhladvhk.cz/info-o-rozvozu.php",
                    new String[] { 
                        STANDARD_DISCLAIMER, 
                        TWOMEALS_DISCLAIMER,
                    });

        if (key.equals(Restaurants.SPORT_CAFE_HK))
            return new Restaurant(
                    "Sport Café",
                    "773 400 600",
                    "http://www.sport-cafe.cz/menu.php",
                    "http://www.sport-cafe.cz/rozvoz.php",
                    new String[] {
                        STANDARD_DISCLAIMER,
                        TWOMEALS_DISCLAIMER,
                    });

        if (key.equals(Restaurants.CAFE_POPULAR_HK))
            return new Restaurant(
                    "Express Popular",
                    "739 350 000",
                    "http://www.expresspopular.cz/denni-menu",
                    "http://www.expresspopular.cz/informace-o-rozvozu",
                    new String[] {
                        STANDARD_DISCLAIMER,
                        TWOMEALS_DISCLAIMER,
                    });

        throw new IllegalArgumentException();
    }

};

