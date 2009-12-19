package webmenu.crawler;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import webmenu.model.*;

public class MamHladHkParserTest
{
    MamHladHkParser parser;

    @Before public void setUp() 
    {
        parser = new MamHladHkParser();
    }

    @Ignore("not implemented yet") @Test public void Parse_ValidHtml_ReturnsFiveItems() throws IOException
    {
        File f = TestUtil.getTestData("mamhladvhk-menu.html");
        System.out.println(f.getCanonicalPath());
        InputStream html = new FileInputStream(f);
        OneDayMenu[] menus = parser.parse(html);

        assertEquals(5, menus.length);
    }
}
