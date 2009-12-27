package webmenu.crawler;

public class CrawlException extends Exception
{
    public CrawlException(String message)
    {
        super(message);
    }

    public CrawlException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
