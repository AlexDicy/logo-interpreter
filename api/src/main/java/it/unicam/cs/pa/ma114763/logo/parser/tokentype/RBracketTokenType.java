package it.unicam.cs.pa.ma114763.logo.parser.tokentype;

import it.unicam.cs.pa.ma114763.logo.parser.TokenType;

import java.util.regex.Pattern;

/**
 * Represents a token type that matches a right bracket ']'
 *
 * @author Lorenzo Lapucci
 */
public class RBracketTokenType extends TokenType {
    public RBracketTokenType() {
        super("R_BRACKET", Pattern.compile("^]"));
    }
}
