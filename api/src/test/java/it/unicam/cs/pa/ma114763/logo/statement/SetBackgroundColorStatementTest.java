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
class SetBackgroundColorStatementTest {

    @Test
    void testSetBackgroundColorStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("SETSCREENCOLOR 0 0 0\nSETSCREENCOLOR 10 20 30 67");
        assertEquals(2, statements.size());
        assertInstanceOf(SetBackgroundColorStatement.class, statements.get(0));
        assertInstanceOf(SetBackgroundColorStatement.class, statements.get(1));
        TestUtils.assertColorStatement((SetBackgroundColorStatement) statements.get(0), (byte) 0, (byte) 0, (byte) 0, (byte) 255);
        TestUtils.assertColorStatement((SetBackgroundColorStatement) statements.get(1), (byte) 10, (byte) 20, (byte) 30, (byte) 67);
    }

    @Test
    void testSetBackgroundColorExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETSCREENCOLOR"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETSCREENCOLOR ABC 0 0"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETSCREENCOLOR 50 0 0 A"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETSCREENCOLOR 10 20 30 40 50"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETSCREENCOLOR 90 7"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("SETSCREENCOLOR -10 20 30"));
    }
}
