package webmenu.crawler;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Logger;

import webmenu.model.*;
import webmenu.data.*;

public class CafePopularCrawler implements Crawler {
   private static final Logger log = Logger.getLogger(CafePopularCrawler.class.getName());

   protected UrlFetcher createFetcher() {
      return new GoogleUrlFetcher();
   }

   protected Parser createParser() {
      return new CafePopularParser();
   }

   protected OneDayMenuStore createStore() {
      return new GoogleOneDayMenuStore();
   }

   public void update(URL url) throws MalformedURLException, IOException, CrawlException {
      boolean defaultUrl = url == null;
      if (defaultUrl) url = new URL("http://www.expresspopular.cz/denni-menu");
      log.fine("Using url '" + url + "'");

      UrlFetcher fetcher = createFetcher();
      Parser parser = createParser();
      OneDayMenuStore store = createStore();

      InputStream webpage = fetcher.fetch(url);
      URL link = new CafePopularLinksParser().parseLinks(url, webpage);
      webpage = fetcher.fetch(link);
      OneDayMenu[] data = parser.parse(webpage);

      for (OneDayMenu menu : data) {
          store.updateOneDayMenu(Restaurants.CAFE_POPULAR_HK, menu);
      }
   }
}
