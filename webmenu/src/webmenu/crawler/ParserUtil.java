package webmenu.crawler;

public class ParserUtil
{
    static String normalizeText(String text)
    {
        return text
            .replaceAll("\\s+", " ")
            .replaceAll("\\s([,“]\\s)", "$1")
            .trim();
    }
}
