package webmenu.crawler;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.*;
import nu.validator.htmlparser.common.*;
import nu.validator.htmlparser.xom.*;
import nu.xom.*;
import webmenu.model.*;

public class SportCafeParser implements Parser {
    final static HtmlBuilder builder = new HtmlBuilder(XmlViolationPolicy.ALTER_INFOSET);
    final static XPathContext xpathContext = new XPathContext("x", "http://www.w3.org/1999/xhtml");

    public OneDayMenu[] parse(InputStream source) throws CrawlException {
        /*
        try {
        */
            // Document doc = builder.build(source);
            // extract days: /html/body/table/tbody/tr[1]/td[1]/div
            // for each day:
            //     extract day name /p[1]/img[1]/@src
            //     extract soup /p[2]
            //     extract meals 1 /p[3]/text()[1-(NN-1)]
            //       & price  /p[3]/text()[NN]
            //       where NN is count(/p[3]/text())
            throw new CrawlException("Not implemented yet");
        /*
        }
        catch (XPathException e) {
            throw new CrawlException("Problem in parsing XPath", e);
        } catch (ParsingException e) {
            throw new CrawlException("Cannot parse HTML document", e);
        } catch (IOException e) {
            throw new CrawlException("Cannot read HTML document", e);
        }
        */
    }
}
