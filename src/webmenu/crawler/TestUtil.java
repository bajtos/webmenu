package webmenu.crawler;

import java.io.File;

class TestUtil
{
    static File getTestData(String file)
    {
        return new File("src/webmenu/crawler/test-data/" + file);
    }
}
