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
        File f = TestUtil.getTestData("mamhladvhk-menu-short.html");
        // System.out.println(f.getCanonicalPath());
        return new FileInputStream(f);
    }

    InputStream getTwoMealsMenuStream() throws FileNotFoundException
    {
        File f = TestUtil.getTestData("mamhladvhk-menu-2meals.html");
        return new FileInputStream(f);
    }

    Document loadDocument(InputStream source) throws ParsingException, IOException
    {
        return builder.build(source);
    }
    
    @Test public void Parse_ShortHtml_ReturnsFourSetAndOneEmptyItems() throws CrawlException, IOException
    {
        InputStream html = getShortMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertFalse(menus[0].isEmpty());
        assertTrue(menus[1].isEmpty());
        assertFalse(menus[2].isEmpty());
        assertFalse(menus[3].isEmpty());
        assertFalse(menus[4].isEmpty());
        assertEquals(5, menus.length);
    }

    @Test public void Parse_TwoMealsHtml_ReturnsThreeSetAndTwoEmptyItems() throws CrawlException, IOException
    {
        InputStream html = getTwoMealsMenuStream();
        OneDayMenu[] menus = parser.parse(html);
        assertEquals(5, menus.length);
        assertFalse(menus[0].isEmpty());
        assertFalse(menus[1].isEmpty());
        assertFalse(menus[2].isEmpty());
        assertTrue(menus[3].isEmpty());
        assertTrue(menus[4].isEmpty());
    }

    @Test public void ParsePrices_ShortHtml_ReturnsCorrectPrices() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        int[] prices = parser.parsePrices(doc);
        assertArrayEquals(new int[] { 71, 77, 85 }, prices);
    }

    @Test public void ParsePrices_TwoMealsHtml_ReturnsCorrectPrices() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getTwoMealsMenuStream());
        int[] prices = parser.parsePrices(doc);
        assertArrayEquals(new int[] { 0, 77, 85 }, prices);
    }

    @Test public void ParseStartDate_ShortHtml_ReturnsCorrectDate() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        Calendar dt = parser.parseStartDate(doc);
        assertEquals(new GregorianCalendar(2009, 11-1, 16).getTime(), dt.getTime());
    }

    @Test public void ParseStartDate_TwoMealsHtml_ReturnsCorrectDate() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getTwoMealsMenuStream());
        Calendar dt = parser.parseStartDate(doc);
        assertEquals(new GregorianCalendar(2009, 12-1, 21).getTime(), dt.getTime());
    }

    String[] parseDay(int day, InputStream source) throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(source);
        Nodes days = parser.parseMenuNodes(doc);
        return parser.parseDay(days.get(day));
    }

    @Test public void ParseDay_MondayShortHtml_ReturnsCorrectNames() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(0, getShortMenuStream());
        String[] expected = new String[] { 
            "Zeleninový krém", 
            "Těstovinový salát se zeleninou a pikantními kuřecími nudličkami",
            "Boloňská směs, špagety, sýr",
            "Kuřecí směs Shanghai, kari rýže" };
        assertArrayEquals(expected, meals);
    }

    @Test public void ParseDay_TuesdayShortHtml_ReturnsNull() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(1, getShortMenuStream());
        assertArrayEquals(null, meals);
    }

    @Test public void Parse_WednesdayShortHtml_ReturnsCorrectNames() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(2, getShortMenuStream());
        String[] expected = new String[] { 
            "Zelná s klobásou", 
            "Květákový mozeček, vařený brambor", 
            "Znojemská hovězí pečeně, rýže",
            "Kuřecí nudličky v smetanovobazalkové omáčce, těstoviny" };
        assertArrayEquals(expected, meals);
    }

    @Test public void ParseDay_MondayTwoMealsHtml_ReturnsCorrectNames() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(0, getTwoMealsMenuStream());
        String[] expected = new String[] { 
            "Česnekový oukrop s šunkou", 
            null,
            "Slovenské halušky se slaninou a zelím",
            "Hovězí maďarský guláš, houskový knedlík" };
        assertArrayEquals(expected, meals);
    }

    @Test public void ParseDay_FridayTwoMealsHtml_ReturnsNull() throws CrawlException, IOException, ParsingException
    {
        String[] meals = parseDay(4, getTwoMealsMenuStream());
        assertArrayEquals(null, meals);
    }

    @Test public void Parse_ShortHtml_MenuItemsAreCorrect() throws CrawlException, IOException
    {
        InputStream html = getShortMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertThat(menus.length, greaterThan(2));

        OneDayMenu m1 = menus[0];
        assertEquals(new GregorianCalendar(2009, 11-1, 16).getTime(), m1.getDay());

        List<SoupItem> soupItems = m1.getSoupItems();
        assertThat(soupItems.size(), equalTo(1));
        assertThat(soupItems.get(0).getName(), equalTo("Polévka"));
        assertThat(soupItems.get(0).getMeal(), equalTo("Zeleninový krém"));

        List<MenuItem> menuItems = m1.getMenuItems();
        assertThat(menuItems.size(), equalTo(3));

        assertThat(menuItems.get(0).getName(), equalTo("Menu 1"));
        assertThat(menuItems.get(0).getMeal(), equalTo("Těstovinový salát se zeleninou a pikantními kuřecími nudličkami"));
        assertThat(menuItems.get(0).getPrice(), equalTo(71));

        assertThat(menuItems.get(1).getName(), equalTo("Menu 2"));
        assertThat(menuItems.get(1).getMeal(), equalTo("Boloňská směs, špagety, sýr"));
        assertThat(menuItems.get(1).getPrice(), equalTo(77));

        assertThat(menuItems.get(2).getName(), equalTo("Menu 3"));
        assertThat(menuItems.get(2).getMeal(), equalTo("Kuřecí směs Shanghai, kari rýže"));
        assertThat(menuItems.get(2).getPrice(), equalTo(85));

        // tuesday was skipped
        assertTrue(menus[1].isEmpty());
        assertEquals(new GregorianCalendar(2009, 11-1, 17).getTime(), menus[1].getDay());

        // verify that the third menu has correct date
        assertEquals(new GregorianCalendar(2009, 11-1, 18).getTime(), menus[2].getDay());
    }

    @Test public void Parse_TwoMealsHtml_MenuItemsAreCorrect() throws CrawlException, IOException
    {
        InputStream html = getTwoMealsMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertThat(menus.length, greaterThan(0));

        OneDayMenu m1 = menus[0];
        assertEquals(new GregorianCalendar(2009, 12-1, 21).getTime(), m1.getDay());

        List<SoupItem> soupItems = m1.getSoupItems();
        assertThat(soupItems.size(), equalTo(1));
        assertThat(soupItems.get(0).getName(), equalTo("Polévka"));
        assertThat(soupItems.get(0).getMeal(), equalTo("Česnekový oukrop s šunkou"));

        List<MenuItem> menuItems = m1.getMenuItems();
        assertThat(menuItems.size(), equalTo(2));

        assertThat(menuItems.get(0).getName(), equalTo("Menu 2"));
        assertThat(menuItems.get(0).getMeal(), equalTo("Slovenské halušky se slaninou a zelím"));
        assertThat(menuItems.get(0).getPrice(), equalTo(77));

        assertThat(menuItems.get(1).getName(), equalTo("Menu 3"));
        assertThat(menuItems.get(1).getMeal(), equalTo("Hovězí maďarský guláš, houskový knedlík"));
        assertThat(menuItems.get(1).getPrice(), equalTo(85));
    }
}
