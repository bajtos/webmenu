package webmenu.crawler;

import java.io.*;
import java.net.*;
import webmenu.model.*;
import webmenu.data.*;

public class MamHladHkCrawler implements Crawler
{
    protected UrlFetcher createFetcher()
    {
        return new GoogleUrlFetcher();
    }

    protected Parser createParser()
    {
        return new MamHladHkParser();
    }

    protected OneDayMenuStore createStore()
    {
        return new GoogleOneDayMenuStore();
    }

    public void update() throws MalformedURLException, IOException, CrawlException
    {
        URL url = new URL("http://www.mamhladvhk.cz/tydenni-menu.php");
        InputStream webpage = createFetcher().fetch(url);
        OneDayMenu[] data = createParser().parse(webpage);
        OneDayMenuStore store = createStore();
        for (OneDayMenu menu : data)
        {
            store.updateOneDayMenu(Restaurants.MAM_HLAD_HK, menu);
        }
    }
}
