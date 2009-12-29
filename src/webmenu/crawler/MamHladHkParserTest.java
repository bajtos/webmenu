package webmenu.crawler;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.util.*;
import nu.validator.htmlparser.common.*;
import nu.validator.htmlparser.xom.*;
import nu.xom.*;
import org.hamcrest.Matcher;
import webmenu.model.*;

public class MamHladHkParserTest
{
    MamHladHkParser parser;
    HtmlBuilder builder;

    @Before public void setUp() 
    {
        parser = new MamHladHkParser();
        builder = new HtmlBuilder(XmlViolationPolicy.ALTER_INFOSET);
        TestUtil.setupLoggingToConsole();
    }

    InputStream getShortMenuStream() throws FileNotFoundException
    {
        File f = TestUtil.getTestData("mamhladvhk-menu.html");
        // System.out.println(f.getCanonicalPath());
        return new FileInputStream(f);
    }

    Document loadDocument(InputStream source) throws ParsingException, IOException
    {
        return builder.build(source);
    }
    
    @Test public void Parse_ShortHtml_ReturnsFourItems() throws CrawlException, IOException
    {
        InputStream html = getShortMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertEquals(4, menus.length);
    }

    @Test public void ParsePrices_ShortHtml_ReturnsCorrectPrices() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        int[] prices = parser.parsePrices(doc);
        assertArrayEquals(new int[] { 71, 77, 85 }, prices);
    }

    @Test public void ParseStartDate_ShortHtml_ReturnsCorrectDate() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        Calendar dt = parser.parseStartDate(doc);
        assertEquals(new GregorianCalendar(2009, 11-1, 16).getTime(), dt.getTime());
    }

    String[] parseDay(int day) throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        Nodes days = parser.parseMenuNodes(doc);
        return parser.parseDay(days.get(day));
    }

    @Test public void ParseDayMonday_ShortHtml_ReturnsCorrectNames() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(0);
        String[] expected = new String[] { 
            "Zeleninový krém", 
            "Těstovinový salát se zeleninou a pikantními kuřecími nudličkami",
            "Boloňská směs, špagety, sýr",
            "Kuřecí směs Shanghai, kari rýže" };
        assertArrayEquals(expected, meals);
    }

    @Test public void ParseDayTuesday_ShortHtml_ReturnsNull() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(1);
        assertArrayEquals(null, meals);
    }

    @Test public void ParseWednesday_ShortHtml_ReturnCorrectNames() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(2);
        String[] expected = new String[] { 
            "Zelná s klobásou", 
            "Květákový mozeček, vařený brambor", 
            "Znojemská hovězí pečeně, rýže",
            "Kuřecí nudličky v smetanovobazalkové omáčce, těstoviny" };
        assertArrayEquals(expected, meals);
    }

    @Test public void Parse_ShortHtml_MenuItemsAreCorrect() throws CrawlException, IOException
    {
        InputStream html = getShortMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertThat(menus.length, greaterThan(2));

        OneDayMenu m1 = menus[0];
        assertEquals(new GregorianCalendar(2009, 11-1, 16).getTime(), m1.getDay().getTime());

        MenuItem[] menuItems = m1.getMenuItems();
        assertThat(menuItems.length, equalTo(3));

        assertThat(menuItems[0].name, equalTo("Menu 1"));
        assertThat(menuItems[0].meal, equalTo("Těstovinový salát se zeleninou a pikantními kuřecími nudličkami"));
        assertThat(menuItems[0].price, equalTo(71));

        assertThat(menuItems[1].name, equalTo("Menu 2"));
        assertThat(menuItems[1].meal, equalTo("Boloňská směs, špagety, sýr"));
        assertThat(menuItems[1].price, equalTo(77));

        assertThat(menuItems[2].name, equalTo("Menu 3"));
        assertThat(menuItems[2].meal, equalTo("Kuřecí směs Shanghai, kari rýže"));
        assertThat(menuItems[2].price, equalTo(85));

        // tuesday was skipped
        // verify that the second menu has correct date
        OneDayMenu m2 = menus[1];
        assertEquals(new GregorianCalendar(2009, 11-1, 18).getTime(), menus[1].getDay().getTime());
    }
}
