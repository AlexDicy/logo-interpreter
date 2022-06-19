package it.unicam.cs.pa.ma114763.logo.parser;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogoTokenizer implements Tokenizer {

    private final List<TokenType> types;

    public LogoTokenizer(List<TokenType> types) {
        this.types = types;
    }

    @Override
    public @Nullable Token nextToken(String input) {
        for (TokenType type : types) {
            if (type.matches(input)) {
                return new Token(input, type);
            }
        }
        return null;
    }
}
