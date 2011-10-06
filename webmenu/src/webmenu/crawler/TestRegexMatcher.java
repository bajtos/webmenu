package webmenu.crawler;

import java.util.regex.Pattern;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class TestRegexMatcher extends BaseMatcher<String> {
    private final Pattern pattern;
    private final String regex;

    public TestRegexMatcher(String regex) {
        this.pattern = Pattern.compile(regex);
        this.regex = "/" + regex + "/";
    }

    public TestRegexMatcher(String regex, int flags) {
        this.pattern = Pattern.compile(regex, flags);
        this.regex = "/" + regex + "/" 
            + ((flags & Pattern.MULTILINE) != 0 ? "m" : "")
            + ((flags & Pattern.DOTALL) != 0 ? "." : "")
            ;
    }
    
    public boolean matches(Object o) {
        return pattern.matcher((String)o).matches();
    }

    public void describeTo(Description description) {
        description.appendText("matches " + regex);
    }

    public static TestRegexMatcher matches(String regex) {
        return new TestRegexMatcher(regex);
    }

    public static TestRegexMatcher matches(String regex, int flags) {
        return new TestRegexMatcher(regex, flags);
    }
}

