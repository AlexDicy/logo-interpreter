package it.unicam.cs.pa.ma114763.logo.parser.exception;

public class InvalidCharactersException extends ParserException {
    public InvalidCharactersException(String inputString) {
        super("Invalid characters found: " + inputString);
    }
}
