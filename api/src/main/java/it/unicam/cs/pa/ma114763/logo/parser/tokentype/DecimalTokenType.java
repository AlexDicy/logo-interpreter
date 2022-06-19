package it.unicam.cs.pa.ma114763.logo.parser.tokentype;

import it.unicam.cs.pa.ma114763.logo.parser.TokenType;

import java.util.regex.Pattern;

/**
 * Represents a token type that matches a decimal number.
 *
 * @author Lorenzo Lapucci
 */
public class DecimalTokenType extends TokenType {
    public DecimalTokenType() {
        super("DECIMAL", Pattern.compile("^\\d+\\.\\d+$"));
    }
}
