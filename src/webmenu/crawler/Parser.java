package webmenu.crawler;

import java.io.InputStream;
import java.io.IOException;
import webmenu.model.OneDayMenu;

public interface Parser
{
    OneDayMenu[] parse(InputStream source) throws CrawlException;
}
