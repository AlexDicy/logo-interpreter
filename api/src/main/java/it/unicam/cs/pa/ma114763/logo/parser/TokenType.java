package it.unicam.cs.pa.ma114763.logo.parser;

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

    public boolean matches(String input) {
        return pattern.matcher(input).matches();
    }
}
