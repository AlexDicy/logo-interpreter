package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IncrementAngleStatementTest {

    @Test
    void testIncrementAngleStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("LEFT 67\nRIGHT 90");
        assertEquals(2, statements.size());
        assertInstanceOf(IncrementAngleStatement.class, statements.get(0));
        assertInstanceOf(IncrementAngleStatement.class, statements.get(1));
        assertEquals(-67, ((IncrementAngleStatement) statements.get(0)).angleIncrement());
        assertEquals(90, ((IncrementAngleStatement) statements.get(1)).angleIncrement());
    }

    @Test
    void testIncrementAngleStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("LEFT"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("LEFT ABC"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("LEFT 50 A"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIGHT"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIGHT 90 7"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("RIGHT -10"));
    }
}
