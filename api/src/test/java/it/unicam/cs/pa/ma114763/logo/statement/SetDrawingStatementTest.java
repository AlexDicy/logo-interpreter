package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetDrawingStatementTest {

    @Test
    void testSetDrawingStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("PENUP\nPENDOWN");
        assertEquals(2, statements.size());
        assertInstanceOf(SetDrawingStatement.class, statements.get(0));
        assertInstanceOf(SetDrawingStatement.class, statements.get(1));
        assertFalse(((SetDrawingStatement) statements.get(0)).draw());
        assertTrue(((SetDrawingStatement) statements.get(1)).draw());
    }

    @Test
    void testSetDrawingStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("PENUP xyz"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("PENDOWN b"));
    }
}
