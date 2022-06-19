package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LogoParser implements Parser {
    private static final List<TokenType> types = List.of(
            new NumberTokenType(),
            new DecimalTokenType(),
            new WordTokenType(),
            new LBracketTokenType(),
            new RBracketTokenType()
    );

    private final Tokenizer tokenizer;

    public LogoParser() {
        this.tokenizer = new LogoTokenizer(types);
    }

    @Override
    public List<Statement> parse(String input) throws ParserException {
        List<Statement> statements = new ArrayList<>();
        // every line is parsed as a statement
        String[] lines = input.split("\n");
        for (String line : lines) {
            String trimmed = line.trim();
            // skip if empty line or a comment
            if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                continue;
            }
            statements.add(parseLine(trimmed));
        }
        return statements;
    }

    private Statement parseLine(String line) throws ParserException {
        // split the input string on whitespace
        String[] strings = line.split("\\s+");
        List<Token> tokens = getTokens(strings);
        return null;
    }

    protected List<Token> getTokens(String[] strings) throws InvalidCharactersException {
        List<Token> tokens = new ArrayList<>();

        for (String string : strings) {
            String trimmed = string.trim();
            // skip empty strings
            if (trimmed.isEmpty()) {
                continue;
            }
            tokens.add(getToken(trimmed));
        }

        return tokens;
    }

    private @NotNull Token getToken(String string) throws InvalidCharactersException {
        Token token = tokenizer.matchToken(string);
        if (token == null) {
            throw new InvalidCharactersException(string);
        }
        return token;
    }
}
