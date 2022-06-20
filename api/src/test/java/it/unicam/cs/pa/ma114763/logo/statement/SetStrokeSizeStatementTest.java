package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetStrokeSizeStatementTest {

    @Test
    void testSetStrokeSizeStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("SETPENSIZE 10");
        assertEquals(1, statements.size());
        assertInstanceOf(SetStrokeSizeStatement.class, statements.get(0));
        assertEquals(10, ((SetStrokeSizeStatement) statements.get(0)).size());

        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENSIZE 0"));
    }

    @Test
    void testSetStrokeSizeStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENSIZE"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENSIZE xyz"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("SETPENSIZE -10"));
    }
}
