package webmenu.crawler;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.*;
import java.util.logging.Logger;

import nu.validator.htmlparser.common.*;
import nu.validator.htmlparser.xom.*;
import nu.xom.*;

public class CafePopularLinksParser {
    private static final Logger log = Logger.getLogger(CafePopularLinksParser.class.getName());

    final static HtmlBuilder builder = new HtmlBuilder(XmlViolationPolicy.ALTER_INFOSET);
    final static XPathContext xpathContext = new XPathContext("x", "http://www.w3.org/1999/xhtml");

    URL[] parseLinks(URL pageUrl, InputStream pageSource) throws CrawlException
    {
        Document doc;
        try {
            doc = builder.build(pageSource);
        } catch (ParsingException e) {
            throw new CrawlException("Cannot parse HTML document", e);
        } catch (IOException e) {
            throw new CrawlException("Cannot read HTML document", e);
        }
        
        String xpath = "//x:div[@id='body_content']//x:a";
        Nodes nodes = doc.query(xpath, xpathContext);
        if (nodes.size() == 0)
            throw new CrawlException("Cannot parse menu-links page: no links found.");

        List<URL> links = new ArrayList<URL>();
        for (int i=0; i < nodes.size(); i++)
        {
            Node link = nodes.get(i);

            Nodes href = link.query("@href");
            if (href.size() == 0)
            {
                log.warning("Skipping <a> without href: " + link.toXML());
                continue;
            }

            URL url;
            try {
                url = new URL(pageUrl, href.get(0).getValue());
            } catch (MalformedURLException e) {
                log.warning("Skipping malformed <a> node (" + e + "): " + link.toXML());
                continue;
            }
            links.add(url);
        }
        return links.toArray(new URL[0]);
    }
}
