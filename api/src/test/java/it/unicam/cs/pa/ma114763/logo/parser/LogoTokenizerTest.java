package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.parser.tokentype.WordTokenType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Lorenzo Lapucci
 */
class LogoTokenizerTest {
    private static LogoTokenizer tokenizer;
    private static List<TokenType> types;

    @BeforeAll
    static void setUp() {
        types = List.of(
                new TokenType("forward", Pattern.compile("^forward$")) {
                },
                new TokenType("left", Pattern.compile("^left$")) {
                },
                new TokenType("decimal", Pattern.compile("^[0-9]+(?:\\.[0-9]+)?$")) {
                },
                new WordTokenType()
        );
        tokenizer = new LogoTokenizer(types);
    }

    @Test
    void testEmptyMatch() {
        List<Token> t1 = tokenizer.matchTokens("forward x");
        assertEquals(0, t1.size());
        List<Token> t2 = tokenizer.matchTokens("-1.2");
        assertEquals(0, t2.size());
    }

    @Test
    void testMatchTokens() {
        List<Token> t1 = tokenizer.matchTokens("forward");
        assertToken(t1, 0, "forward");
        List<Token> t2 = tokenizer.matchTokens("left");
        assertToken(t2, 1, "left");
        List<Token> t3 = tokenizer.matchTokens("1.0");
        assertToken(t3, 2, "1.0");
        List<Token> t4 = tokenizer.matchTokens("17");
        assertToken(t4, 2, "17");
        List<Token> t5 = tokenizer.matchTokens("randomWord");
        assertToken(t5, 3, "randomWord");
    }

    /**
     * Asserts that the list of tokens contains exactly one token with the given type and value.
     *
     * @param tokens         the list of tokens to check
     * @param tokenTypeIndex the index of the token type to check against
     * @param text           the expected text of the token
     */
    private static void assertToken(List<Token> tokens, int tokenTypeIndex, String text) {
        assertEquals(1, tokens.size());
        assertInstanceOf(types.get(tokenTypeIndex).getClass(), tokens.get(0).type());
        assertEquals(text, tokens.get(0).text());
    }
}
