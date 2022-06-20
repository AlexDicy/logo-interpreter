package it.unicam.cs.pa.ma114763.logo.parser.exception;

import it.unicam.cs.pa.ma114763.logo.parser.Token;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lorenzo Lapucci
 */
public class InvalidSyntaxException extends ParserException {
    public InvalidSyntaxException(List<Token> tokens, String message) {
        super("Invalid syntax: \"" + tokens.stream()
                .map(Token::text)
                .collect(Collectors.joining(" ")) + "\" " + message);
    }

    public InvalidSyntaxException(String string, String message) {
        super("Invalid syntax: \"" + string + "\" " + message);
    }

    public InvalidSyntaxException(Token token, String message) {
        this(token.text(), message);
    }
}
