package webmenu.crawler;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

import webmenu.model.*;
import webmenu.data.*;

public class MamHladHkCrawler implements Crawler
{
    private static final Logger log = Logger.getLogger(MamHladHkCrawler.class.getName());

    protected UrlFetcher createFetcher()
    {
        return new GoogleUrlFetcher();
    }

    protected Parser createParser()
    {
        return new MamHladHkParser();
    }

    protected DataStore createStore()
    {
        return new GoogleDataStore();
    }

    public void update(URL url) throws MalformedURLException, IOException, CrawlException
    {
        if (url == null) url = new URL("http://www.mamhladvhk.cz/tydenni-menu.php");
        log.fine("Using url '" + url + "'");

        InputStream webpage = createFetcher().fetch(url);
        OneDayMenu[] data = createParser().parse(webpage);
        DataStore store = createStore();
        for (OneDayMenu menu : data)
        {
            store.updateOneDayMenu(Restaurants.MAM_HLAD_HK, menu);
        }
    }
}
