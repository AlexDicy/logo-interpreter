package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.TestUtils;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidCharactersException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lorenzo Lapucci
 */
class SetStrokeColorStatementTest {

    @Test
    void testSetStrokeColorStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("SETPENCOLOR 0 0 0\nSETPENCOLOR 10 20 30 67");
        assertEquals(2, statements.size());
        assertInstanceOf(SetStrokeColorStatement.class, statements.get(0));
        assertInstanceOf(SetStrokeColorStatement.class, statements.get(1));
        TestUtils.assertColorStatement((SetStrokeColorStatement) statements.get(0), 0, 0, 0, 255);
        TestUtils.assertColorStatement((SetStrokeColorStatement) statements.get(1), 10, 20, 30, 67);
    }

    @Test
    void testSetStrokeColorStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENCOLOR"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENCOLOR ABC 0 0"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENCOLOR 50 0 0 A"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENCOLOR 10 20 30 40 50"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETPENCOLOR 90 7"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("SETPENCOLOR -10 20 30"));
    }
}
