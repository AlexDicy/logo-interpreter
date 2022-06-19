package it.unicam.cs.pa.ma114763.logo.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Abstract class for all the token types.
 *
 * @author Lorenzo Lapucci
 */
public abstract class TokenType {
    private final String name;
    private final Pattern pattern;

    public TokenType(String name, Pattern pattern) {
        this.name = name;
        this.pattern = pattern;
    }

    /**
     * @param input the string of characters
     * @return 0 if the token doesn't match, the length of the matched string otherwise
     */
    public int matches(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.end();
        }
        return 0;
    }
}
