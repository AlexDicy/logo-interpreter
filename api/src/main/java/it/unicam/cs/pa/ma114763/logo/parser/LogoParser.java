package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.*;

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
        // split the input string on whitespace
        String[] strings = input.split("\\s+");
        List<Token> tokens = getTokens(strings);
        return null;
    }

    protected List<Token> getTokens(String[] strings) throws InvalidCharactersException {
        List<Token> tokens = new ArrayList<>();

        for (String string : strings) {
            Token token = tokenizer.matchToken(string);
            if (token == null) {
                throw new InvalidCharactersException(string);
            }
            tokens.add(token);
        }

        return tokens;
    }
}
