package it.unicam.cs.pa.ma114763.logo.parser.tokentype;

import it.unicam.cs.pa.ma114763.logo.parser.TokenType;

import java.util.regex.Pattern;

/**
 * Represents a token type that matches a word without spaces nor other non-alphabetical characters.
 *
 * @author Lorenzo Lapucci
 */
public class WordTokenType extends TokenType {
    public WordTokenType() {
        super("WORD", Pattern.compile("^[a-zA-Z]+$"));
    }
}
