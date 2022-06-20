package it.unicam.cs.pa.ma114763.logo.parser;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoTokenizer implements Tokenizer {

    private final List<TokenType> types;

    public LogoTokenizer(List<TokenType> types) {
        this.types = types;
    }

    @Override
    public @Nullable List<Token> matchTokens(String input) {
        List<Token> tokens = new ArrayList<>(1);
        while (input.length() > 0) {
            int matchedSize = matchTokenAndAdd(input, tokens);
            if (matchedSize == 0) {
                return Collections.emptyList();
            }
            input = input.substring(matchedSize);
        }
        return tokens;
    }

    private int matchTokenAndAdd(String input, List<Token> tokens) {
        for (TokenType type : types) {
            int size = type.matches(input);
            if (size > 0) {
                tokens.add(new Token(input.substring(0, size), type));
                return size;
            }
        }
        return 0;
    }
}
