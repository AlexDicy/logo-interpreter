package it.unicam.cs.pa.ma114763.logo.parser;

import java.util.List;

/**
 * A class that matches tokens to strings.
 *
 * @author Lorenzo Lapucci
 */
public interface Tokenizer {
    /**
     * Takes a string of characters and returns the corresponding token.
     * If part of the string is not a token, it returns an empty list.
     *
     * @param input the string of characters
     * @return the matched token list or an empty list if part of the string is not a token
     */
    List<Token> matchTokens(String input);
}
