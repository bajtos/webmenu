package webmenu.crawler;

import java.net.*;
import java.io.*;

class GoogleUrlFetcher implements UrlFetcher
{
    public InputStream fetch(URL url) throws IOException
    {
        // TODO: send if-modified-since header, compute MD5 checksum from the response, etc.
        return url.openStream();
    }
}
