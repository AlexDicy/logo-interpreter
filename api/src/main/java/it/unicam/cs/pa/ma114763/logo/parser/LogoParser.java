package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.parser.tokentype.*;

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
        //List<Token> tokens = tokenizer.tokenize(input, types);
        return null;
    }
}
