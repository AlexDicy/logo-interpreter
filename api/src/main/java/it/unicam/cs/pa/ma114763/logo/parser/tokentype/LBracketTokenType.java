package it.unicam.cs.pa.ma114763.logo.parser.tokentype;

import it.unicam.cs.pa.ma114763.logo.parser.TokenType;

import java.util.regex.Pattern;

/**
 * Represents a token type that matches a left bracket '['
 *
 * @author Lorenzo Lapucci
 */
public class LBracketTokenType extends TokenType {
    public LBracketTokenType() {
        super("L_BRACKET", Pattern.compile("^\\[+$"));
    }
}
