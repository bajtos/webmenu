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

    @Test public void ParseMenuLinks_SomePage_ReturnsCorrectLinks() throws Exception {
        URL[] links = new CafePopularLinksParser().parseLinks(new URL("http://www.expresspopular.cz/some-page.html"), getMenuLinkStream());
        assertThat(links,
                equalTo(new URL[] {
                    new URL("http://www.expresspopular.cz/ew/a50d6dc6-0a34-4d75-936f-ab3273228bfd-cs"),
                    new URL("http://www.expresspopular.cz/ew/540294a7-171d-4ea6-902b-1235745661eb-cs")
                }));
    }
}

