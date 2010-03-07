package webmenu.crawler;

public class ParserUtil
{
    static String normalizeText(String text)
    {
        return text.replaceAll("\\s+", " ").trim();
    }
}
