package webmenu.crawler;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

import webmenu.model.*;
import webmenu.data.*;

public class SportCafeCrawler implements Crawler {
   private static final Logger log = Logger.getLogger(SportCafeCrawler.class.getName());

   protected UrlFetcher createFetcher() {
      return new GoogleUrlFetcher();
   }

   protected Parser createParser() {
      return new SportCafeParser();
   }

   protected DataStore createStore() {
      return new GoogleDataStore();
   }

   public void update(URL url) throws MalformedURLException, IOException, CrawlException {
      boolean defaultUrl = url == null;
      if (defaultUrl) url = new URL("http://www.sport-cafe.cz/menu.php");
      log.fine("Using url '" + url + "'");

      InputStream webpage = createFetcher().fetch(url);
      OneDayMenu[] data = createParser().parse(webpage);
      DataStore store = createStore();

      Calendar now = Calendar.getInstance();
      Calendar today = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

      for (OneDayMenu menu : data) {
         // non-default url are used for development checks
         if (menu.getDay().before(today.getTime()) && defaultUrl)
            continue;
         store.updateOneDayMenu(Restaurants.SPORT_CAFE_HK, menu);
      }
   }
}
