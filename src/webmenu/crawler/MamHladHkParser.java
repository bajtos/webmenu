package webmenu.crawler;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.*;
import nu.validator.htmlparser.common.*;
import nu.validator.htmlparser.xom.*;
import nu.xom.*;
import webmenu.model.*;

public class MamHladHkParser implements Parser
{
    final static Pattern MenuPricePattern = Pattern.compile("^Menu (\\d): (\\d+),- Kč$");
    final static Pattern StartDatePattern = Pattern.compile("^(\\d+)\\.(\\d+)\\. - (\\d+)\\.(\\d+)\\.(\\d+) od 11 do 15 hod.$");

    final static HtmlBuilder builder = new HtmlBuilder(XmlViolationPolicy.ALTER_INFOSET);
    final static XPathContext xpathContext = new XPathContext("x", "http://www.w3.org/1999/xhtml");

    private static final Logger log = Logger.getLogger(MamHladHkParser.class.getName());

    int[] parsePrices(Document doc) throws CrawlException
    {
        int[] prices = new int[3];

        for (int ix = 0; ix < 3; ix++)
        {
            String menuNumber = String.valueOf(ix+1);
            String xpath = "//x:div[@id='main']/x:div[@class='columnright']/x:div[@class='graybox']//x:span[@class='menu" + menuNumber + "']";
            Nodes nodes = doc.query(xpath, xpathContext);

            if (nodes.size() != 1)
                throw new CrawlException("Cannot parse price for Menu" + menuNumber + " : nodes.size() is " + nodes.size());

            String text = nodes.get(0).getValue();

            Matcher m = MenuPricePattern.matcher(text);
            if (!m.matches())
                throw new CrawlException("Malformed price text: '" + text + "'");

            String num = m.group(1);
            if (!num.equals(menuNumber))
                throw new CrawlException("Malformed price text: expected '" + menuNumber + "' but was '" + num + "'; text: '" + text + "'");

            String priceString = m.group(2);

            try
            {
                prices[ix] = Integer.parseInt(priceString);
            }
            catch (NumberFormatException e)
            {
                throw new CrawlException("Canot parse price string '" + priceString + "' in text '" + text + "'", e);
            }
        }
        return prices;
    }

    Date parseStartDate(Document doc) throws CrawlException
    {
        String xpath = "//x:div[@id='main']/x:div[@class='columnleft3r']/x:p[1]/x:em";
        Nodes nodes = doc.query(xpath, xpathContext);

        if (nodes.size() != 1)
            throw new CrawlException("Cannot parse start date: nodes.size() is " + nodes.size());

        String text = nodes.get(0).getValue();

        Matcher m = StartDatePattern.matcher(text);

        if (!m.matches())
            throw new CrawlException("Malformed date text: '" + text + "'");

        try
        {
            int year = Integer.parseInt(m.group(5));
            int month = Integer.parseInt(m.group(2));
            int day = Integer.parseInt(m.group(1));
            if (year < 2008 || year > 2100)
                throw new CrawlException("Suspicious date: '" + text + "', the year " + year + " is out of range");
            return new GregorianCalendar(year, month - 1, day).getTime();
        }
        catch (NumberFormatException e)
        {
            throw new CrawlException("Canot parse date from text '" + text + "'", e);
        }
    }

    Nodes parseMenuNodes(Document doc) throws CrawlException
    {
        String xpath = "//x:div[@id='main']/x:div[@class='columnleft3r']/x:h4/following-sibling::x:p";
        Nodes nodes = doc.query(xpath, xpathContext);
        if (nodes.size() != 5)
            throw new CrawlException("Cannot parse menu nodes: nodes.size() is " + nodes.size());
        return nodes;
    }

    String[] parseSoups(Document doc) throws CrawlException
    {
        Nodes nodes = parseMenuNodes(doc);

        String[] retval = new String[5];
        for (int ix=0; ix<nodes.size(); ix++)
        {
            Node n = nodes.get(ix);
            if (n.getChildCount() < 3)
            {
                log.info("Skipping possibly empty menu node:\n" + n.toXML());
                continue;
            }

            if (!(n.getChild(0) instanceof Element && n.getChild(0).getValue().trim().equals("Polévka:") && n.getChild(1) instanceof Text))
            {
                log.info("Skipping menu node without soup:\n" + n.toXML());
                continue;
            }

            String soup = n.getChild(1).getValue().trim();
            log.fine("soup" + ix + ": " + soup);
            retval[ix] = soup;
        }
        return retval;
    }

    public OneDayMenu[] parse(InputStream source) throws CrawlException
    {
        try
        {
            Document doc = builder.build(source);

            return new OneDayMenu[0];
        }
        catch (XPathException e)
        {
            throw new CrawlException("Problem in parsing XPath", e);
        }
        catch (ParsingException e)
        {
            throw new CrawlException("Cannot parse HTML document", e);
        }
        catch (IOException e)
        {
            throw new CrawlException("Cannot read HTML document", e);
        }
    }
}

