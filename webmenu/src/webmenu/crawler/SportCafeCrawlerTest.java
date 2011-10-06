package webmenu.crawler;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.jmock.*;
import org.junit.runner.RunWith;
import org.jmock.integration.junit4.*;

import webmenu.data.*;
import webmenu.model.*;
import java.io.*;
import java.net.*;
import java.util.*;

@RunWith(JMock.class)
public class SportCafeCrawlerTest
{
    Mockery mockery = new JUnit4Mockery();

    @Test public void Fetch_TwoDaysReturned_UpdatesTwoDays() throws MalformedURLException, IOException, CrawlException
    {
        final UrlFetcher fetcher = mockery.mock(UrlFetcher.class);
        final Parser parser = mockery.mock(Parser.class);
        final DataStore store = mockery.mock(DataStore.class);

        final InputStream content = new ByteArrayInputStream(new byte[0]);
        Calendar now = Calendar.getInstance();
        Calendar date1 = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        Calendar date2 = date1; date2.add(Calendar.DAY_OF_MONTH, 1);
        final OneDayMenu m1 = new OneDayMenu(date1.getTime(), null, null);
        final OneDayMenu m2 = new OneDayMenu(date2.getTime(), null, null);

        mockery.checking(new Expectations() {{
           oneOf(fetcher).fetch(new URL("http://www.sport-cafe.cz/menu.php")); will(returnValue(content));
            oneOf(parser).parse(content); will(returnValue(new OneDayMenu[] { m1, m2 }));
            oneOf(store).updateOneDayMenu(Restaurants.SPORT_CAFE_HK, m1);
            oneOf(store).updateOneDayMenu(Restaurants.SPORT_CAFE_HK, m2);
        }});

        CustomCrawler crawler = new CustomCrawler(fetcher, parser, store);
        crawler.update(null);

        mockery.assertIsSatisfied();
    }

    @Test public void Fetch_DayIsInPast_MenuIsDiscarded() throws MalformedURLException, IOException, CrawlException
    {
        final UrlFetcher fetcher = mockery.mock(UrlFetcher.class);
        final Parser parser = mockery.mock(Parser.class);
        final DataStore store = mockery.mock(DataStore.class);

        final InputStream content = new ByteArrayInputStream(new byte[0]);

        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -1);
        final OneDayMenu m = new OneDayMenu(date.getTime(), null, null);

        mockery.checking(new Expectations() {{
           oneOf(fetcher).fetch(new URL("http://www.sport-cafe.cz/menu.php")); will(returnValue(content));
            oneOf(parser).parse(content); will(returnValue(new OneDayMenu[] { m }));
            never(store).updateOneDayMenu(with(any(String.class)), with(any(OneDayMenu.class)));
        }});

        CustomCrawler crawler = new CustomCrawler(fetcher, parser, store);
        crawler.update(null);

        mockery.assertIsSatisfied();
    }
    //
    // TODO error & exception handling

    class CustomCrawler extends SportCafeCrawler
    {
        private UrlFetcher fetcher;
        private Parser parser;
        private DataStore store;

        public CustomCrawler(UrlFetcher fetcher, Parser parser, DataStore store)
        {
            this.fetcher = fetcher;
            this.parser = parser;
            this.store = store;
        }

        @Override
        protected UrlFetcher createFetcher()
        {
            return fetcher;
        }

        @Override
        protected Parser createParser()
        { 
            return parser;
        }

        @Override
        protected DataStore createStore()
        {
            return store;
        }
    }
}
