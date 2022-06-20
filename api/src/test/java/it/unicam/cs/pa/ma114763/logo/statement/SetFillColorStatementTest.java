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

class SetFillColorStatementTest {

    @Test
    void testSetFillColorStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("SETFILLCOLOR 0 0 0\nSETFILLCOLOR 10 20 30 67");
        assertEquals(2, statements.size());
        assertInstanceOf(SetFillColorStatement.class, statements.get(0));
        assertInstanceOf(SetFillColorStatement.class, statements.get(1));
        TestUtils.assertColorStatement((SetFillColorStatement) statements.get(0), (byte) 0, (byte) 0, (byte) 0, (byte) 255);
        TestUtils.assertColorStatement((SetFillColorStatement) statements.get(1), (byte) 10, (byte) 20, (byte) 30, (byte) 67);
    }

    @Test
    void testSetFillColorStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETFILLCOLOR"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETFILLCOLOR ABC 0 0"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETFILLCOLOR 50 0 0 A"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETFILLCOLOR 10 20 30 40 50"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("SETFILLCOLOR 90 7"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("SETFILLCOLOR -10 20 30"));
    }
}
