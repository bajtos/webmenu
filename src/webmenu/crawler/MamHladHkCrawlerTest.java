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
public class MamHladHkCrawlerTest
{
    Mockery mockery = new JUnit4Mockery();

    @Test public void Fetch_TwoDaysReturned_UpdatesTwoDays() throws MalformedURLException, IOException, CrawlException
    {
        final UrlFetcher fetcher = mockery.mock(UrlFetcher.class);
        final Parser parser = mockery.mock(Parser.class);
        final OneDayMenuStore store = mockery.mock(OneDayMenuStore.class);

        final InputStream content = new ByteArrayInputStream(new byte[0]);
        final OneDayMenu m1 = new OneDayMenu(new GregorianCalendar(2009, 10, 1), null, null);
        final OneDayMenu m2 = new OneDayMenu(new GregorianCalendar(2009, 10, 2), null, null);

        mockery.checking(new Expectations() {{
            oneOf(fetcher).fetch(new URL("http://www.mamhladvhk.cz/tydenni-menu.php")); will(returnValue(content));
            oneOf(parser).parse(content); will(returnValue(new OneDayMenu[] { m1, m2 }));
            oneOf(store).updateOneDayMenu(Restaurants.MAM_HLAD_HK, m1);
            oneOf(store).updateOneDayMenu(Restaurants.MAM_HLAD_HK, m2);
        }});

        CustomCrawler crawler = new CustomCrawler(fetcher, parser, store);
        crawler.fetch();

        mockery.assertIsSatisfied();
    }

    // TODO error & exception handling

    class CustomCrawler extends MamHladHkCrawler
    {
        private UrlFetcher fetcher;
        private Parser parser;
        private OneDayMenuStore store;

        public CustomCrawler(UrlFetcher fetcher, Parser parser, OneDayMenuStore store)
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
        protected OneDayMenuStore createStore()
        {
            return store;
        }
    }
}
