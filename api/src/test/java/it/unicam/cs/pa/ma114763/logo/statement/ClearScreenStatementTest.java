package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lorenzo Lapucci
 */
class ClearScreenStatementTest {
    @Test
    void testClearScreenStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("CLEARSCREEN");
        assertEquals(1, statements.size());
        assertInstanceOf(ClearScreenStatement.class, statements.get(0));

        assertThrows(UnknownCommandException.class, () -> parser.parse("CLEARSCREN"));
        assertThrows(UnknownCommandException.class, () -> parser.parse("CLEAR"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("CLEARSCREEN a"));
    }
}
