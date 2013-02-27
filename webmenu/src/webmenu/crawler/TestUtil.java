package webmenu.crawler;

import java.io.File;
import java.util.logging.*;

class TestUtil
{
    static File getTestData(String file)
    {
        return new File("src/webmenu/crawler/test-data/" + file);
    }

    static void setupLoggingToConsole()
    {
        Logger global = Logger.getLogger("");
        global.setLevel(Level.ALL);

        Handler[] handlers = global.getHandlers();
        for (int i = 0; i < handlers.length; i++)
        {
            Handler h = handlers[i];
            if (h instanceof ConsoleHandler)
                h.setLevel(Level.ALL);
        }
    }
}
