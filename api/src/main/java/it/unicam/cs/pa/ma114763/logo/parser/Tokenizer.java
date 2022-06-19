package it.unicam.cs.pa.ma114763.logo.parser;

import org.jetbrains.annotations.Nullable;

/**
 * A class that matches tokens to strings.
 *
 * @author Lorenzo Lapucci
 */
public interface Tokenizer {
    /**
     * Takes a string of characters and returns the corresponding token.
     *
     * @param input the string of characters
     * @return the matched token or null if no token was matched
     */
    @Nullable Token nextToken(String input);
}
