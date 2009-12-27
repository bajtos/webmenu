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
    
    @Ignore("not implemented yet") @Test public void Parse_ValidHtml_ReturnsFiveItems() throws CrawlException, IOException
    {
        InputStream html = getShortMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertEquals(5, menus.length);
    }

    @Test public void ParsePrices_SomeHtml_ReturnsCorrectPrices() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        int[] prices = parser.parsePrices(doc);
        assertArrayEquals(new int[] { 71, 77, 85 }, prices);
    }

    @Test public void ParseStartDate_SomeHtml_ReturnsCorrectDate() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        Date dt = parser.parseStartDate(doc);
        assertEquals(new GregorianCalendar(2009, 11-1, 16).getTime(), dt);
    }

    @Test public void ParseSoups_SomeHtml_ReturnsCorrectNames() throws CrawlException, IOException, ParsingException
    {
        Document doc = loadDocument(getShortMenuStream());
        String[] soups = parser.parseSoups(doc);
        assertArrayEquals(new String[] { "Zeleninový krém", null, "Zelná s klobásou", "Krkonošské kyselo", "Bramboračka" }, soups);
    }

    /*
    @Test public void Parse_ValidHtml_MenuItemsAreCorrect() throws CrawlException, IOException
    {
        InputStream html = getShortMenuStream();
        OneDayMenu[] menus = parser.parse(html);

        assertThat(menus.length, greaterThan(1));

        OneDayMenu m1 = menus[0];
        assertThat(m1.getDay(), equalTo(Date(2009, 11, 16)));

        MenuItem[] menuItems = m1.getMenuItems();
        assertThat(menuItems.length, equalTo(3));

        assertThat(menuItems[0].name, equalTo("Menu 1"));
        assertThat(menuItems[0].meal, equalTo("Těstovinový salát se zeleninou a pikantními kuřecími nudličkami"));
        assertThat(menuItems[0].price, equalTo(71));

        assertThat(menuItems[1].name, equalTo("Menu 2"));
        assertThat(menuItems[1].meal, equalTo("Boloňská směs, špagety, sýr"));
        assertThat(menuItems[1].price, equalTo(77));

        assertThat(menuItems[1].name, equalTo("Menu 3"));
        assertThat(menuItems[1].meal, equalTo("Kuřecí směs Shanghai, kari rýže"));
        assertThat(menuItems[1].price, equalTo(85));
    }
    */
}
