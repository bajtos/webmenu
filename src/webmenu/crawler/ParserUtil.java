package webmenu.crawler;

public class ParserUtil
{
    static String normalizeText(String text)
    {
        return text
            .replace('\n', ' ')
            .replace('\r', ' ')
            .replace('\t', ' ')
            .trim()
            ;
    }
}
