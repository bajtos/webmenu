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
public class CafePopularCrawlerTest
{
    Mockery mockery = new JUnit4Mockery();
    
    @Test public void Fetch_TwoLinksReturned_UpdatesAllMenus() throws Exception
    {
        final URL baseUrl = new URL("http://www.expresspopular.cz/denni-menu");
        final UrlFetcher fetcher = mockery.mock(UrlFetcher.class);
        final Parser parser = mockery.mock(Parser.class);
        final OneDayMenuStore store = mockery.mock(OneDayMenuStore.class);

        final InputStream links0 = CafePopularLinksParserTest.getMenuLinkStream();
        final URL[] urls = new CafePopularLinksParser().parseLinks(baseUrl, links0);
        assertThat(urls, arrayWithSize(2));

        final InputStream links = CafePopularLinksParserTest.getMenuLinkStream();

        final InputStream content1 = new ByteArrayInputStream(new byte[0]);
        final InputStream content2 = new ByteArrayInputStream(new byte[0]);
        final OneDayMenu m1 = new OneDayMenu(new Date(), null, null);
        final OneDayMenu m2 = new OneDayMenu(new Date(), null, null);
        final OneDayMenu m3 = new OneDayMenu(new Date(), null, null);


        mockery.checking(new Expectations() {{
           oneOf(fetcher).fetch(baseUrl); will(returnValue(links));
           oneOf(fetcher).fetch(urls[0]); will(returnValue(content1));
           oneOf(fetcher).fetch(urls[1]); will(returnValue(content2));
           oneOf(parser).parse(content1); will(returnValue(new OneDayMenu[] { m1, m2 }));
           oneOf(parser).parse(content2); will(returnValue(new OneDayMenu[] { m3 }));
           oneOf(store).updateOneDayMenu(Restaurants.CAFE_POPULAR_HK, m1);
           oneOf(store).updateOneDayMenu(Restaurants.CAFE_POPULAR_HK, m2);
           oneOf(store).updateOneDayMenu(Restaurants.CAFE_POPULAR_HK, m3);
        }});

        CustomCrawler crawler = new CustomCrawler(fetcher, parser, store);
        crawler.update(null);

        mockery.assertIsSatisfied();
    }

    class CustomCrawler extends CafePopularCrawler
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
