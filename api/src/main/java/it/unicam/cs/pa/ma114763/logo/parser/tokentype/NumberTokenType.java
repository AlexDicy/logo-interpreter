package it.unicam.cs.pa.ma114763.logo.parser.tokentype;

import it.unicam.cs.pa.ma114763.logo.parser.TokenType;

import java.util.regex.Pattern;

/**
 * Represents a token type that matches a number.
 *
 * @author Lorenzo Lapucci
 */
public class NumberTokenType extends TokenType {
    public NumberTokenType() {
        super("NUMBER", Pattern.compile("^\\d+$"));
    }
}
