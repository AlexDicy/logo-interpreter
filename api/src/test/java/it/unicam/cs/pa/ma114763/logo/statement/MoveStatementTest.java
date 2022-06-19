package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveStatementTest {

    @Test
    void testMoveStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("FORWARD 10\nBACKWARD 900");
        assertEquals(2, statements.size());
        assertInstanceOf(MoveStatement.class, statements.get(0));
        assertInstanceOf(MoveStatement.class, statements.get(1));
        MoveStatement statement1 = (MoveStatement) statements.get(0);
        MoveStatement statement2 = (MoveStatement) statements.get(1);
        assertEquals(10, statement1.distance());
        assertFalse(statement1.backward());
        assertEquals(900, statement2.distance());
        assertTrue(statement2.backward());
    }

    @Test
    void testMoveStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("FORWARD"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("FORWARD xyz"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("FORWARD 50 b"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("BACKWARD"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("BACKWARD 6 7"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("BACKWARD -900"));
    }
}
