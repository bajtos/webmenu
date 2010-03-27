package webmenu.crawler;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.net.URL;

public class CafePopularLinksParserTest {
    @Before public void setUp() {
        TestUtil.setupLoggingToConsole();
    }

    static InputStream getMenuLinkStream() throws Exception {
        File f = TestUtil.getTestData("cafe-popular-menulinks.html");
        return new FileInputStream(f);
    }

    @Test public void ParseMenuLinks_SomePage_ReturnsCorrectLink() throws Exception {
        URL link = new CafePopularLinksParser().parseLinks(new URL("http://www.expresspopular.cz/some-page.html"), getMenuLinkStream());
        assertThat(link, equalTo(new URL("http://www.expresspopular.cz/ew/540294a7-171d-4ea6-902b-1235745661eb-cs")));
    }
}

