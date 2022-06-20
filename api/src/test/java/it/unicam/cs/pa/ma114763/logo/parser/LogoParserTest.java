package it.unicam.cs.pa.ma114763.logo.parser;

import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.DecimalTokenType;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.NumberTokenType;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.WordTokenType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lorenzo Lapucci
 */
class LogoParserTest {
    private final LogoParser parser = new LogoParser();

    @Test
    void testTokens() throws InvalidCharactersException {
        List<Token> tokens = getTokens("move 10 10.7");
        assertEquals(3, tokens.size());
        assertToken(tokens.get(0), "move", WordTokenType.class);
        assertToken(tokens.get(1), "10", NumberTokenType.class);
        assertToken(tokens.get(2), "10.7", DecimalTokenType.class);

        assertThrows(InvalidCharactersException.class, () -> getTokens("mo1ve"));
        assertThrows(InvalidCharactersException.class, () -> getTokens("10A 10.7"));
        assertThrows(InvalidCharactersException.class, () -> getTokens("move 10 10,7"));
        assertThrows(InvalidCharactersException.class, () -> getTokens("move -10"));
    }

    private List<Token> getTokens(String input) throws InvalidCharactersException {
        String[] strings = input.split("\\s+");
        return parser.getTokens(strings);
    }

    private void assertToken(Token token, String text, Class<? extends TokenType> type) {
        assertEquals(text, token.text());
        assertInstanceOf(type, token.type());
    }
}
