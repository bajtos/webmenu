package webmenu.crawler;

import java.io.IOException;

public interface Crawler {
    void update() throws CrawlException, IOException;
}
