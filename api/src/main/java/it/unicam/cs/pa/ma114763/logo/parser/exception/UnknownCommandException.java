package it.unicam.cs.pa.ma114763.logo.parser.exception;

public class UnknownCommandException extends ParserException {

    public UnknownCommandException(String command) {
        super("Unknown command: \"" + command + "\"");
    }
}
