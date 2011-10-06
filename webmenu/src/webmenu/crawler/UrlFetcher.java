package webmenu.crawler;

import java.net.URL;
import java.io.InputStream;
import java.io.IOException;

/// Class for fetching web-pages.
/// This class encapsulates Google URL Fetch API
/// and (optionally) provides caching
public interface UrlFetcher
{
    InputStream fetch(URL url) throws IOException;
}
