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

public class SportCafeParserTest {
    SportCafeParser parser;
    HtmlBuilder builder;

    @Before public void setUp() {
        parser = new SportCafeParser();
        builder = new HtmlBuilder(XmlViolationPolicy.ALTER_INFOSET);
        TestUtil.setupLoggingToConsole();
    }

    InputStream getShortMenuStream() throws FileNotFoundException
    {
        File f = TestUtil.getTestData("sport-cafe-menu-short.html");
        // System.out.println(f.getCanonicalPath());
        return new FileInputStream(f);
    }

    @Test public void Dummy()
    {
        assertTrue(false);
    }
}
