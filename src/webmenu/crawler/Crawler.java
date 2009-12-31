package webmenu.crawler;

import java.io.IOException;
import java.net.URL;

public interface Crawler {
    /// @param customURL null for the default URL
    void update(URL customUrl) throws CrawlException, IOException;
}
