package it.unicam.cs.pa.ma114763.logo.parser.exception;

import it.unicam.cs.pa.ma114763.logo.parser.Parser;

/**
 * Used by {@link Parser} to signal that an error occurred while parsing.
 *
 * @author Lorenzo Lapucci
 */
public class ParserException extends Exception {
    public ParserException(String message) {
        super(message);
    }
}
