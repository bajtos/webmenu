package webmenu.crawler;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

import webmenu.model.*;
import webmenu.data.*;

public class SportCafeCrawler implements Crawler
{
    private static final Logger log = Logger.getLogger(SportCafeCrawler.class.getName());

    protected UrlFetcher createFetcher()
    {
        return new GoogleUrlFetcher();
    }

    protected Parser createParser()
    {
        return new SportCafeParser();
    }

    protected OneDayMenuStore createStore()
    {
        return new GoogleOneDayMenuStore();
    }

    public void update(URL url) throws MalformedURLException, IOException, CrawlException
    {
        if (url == null) url = new URL("http://www.sport-cafe.cz/menu.php");
        log.fine("Using url '" + url + "'");

        InputStream webpage = createFetcher().fetch(url);
        OneDayMenu[] data = createParser().parse(webpage);
        OneDayMenuStore store = createStore();

        Calendar now = Calendar.getInstance();
        Calendar today = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        for (OneDayMenu menu : data)
        {
            if (menu.getDay().before(today.getTime()))
                continue;
            store.updateOneDayMenu(Restaurants.SPORT_CAFE_HK, menu);
        }
    }
}
