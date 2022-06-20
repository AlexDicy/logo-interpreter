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

class RotateAngleStatementTest {

    @Test
    void testRotateAngleStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("LEFT 67\nRIGHT 90");
        assertEquals(2, statements.size());
        assertInstanceOf(RotateAngleStatement.class, statements.get(0));
        assertInstanceOf(RotateAngleStatement.class, statements.get(1));
        assertEquals(-67, ((RotateAngleStatement) statements.get(0)).angleRotation());
        assertEquals(90, ((RotateAngleStatement) statements.get(1)).angleRotation());
    }

    @Test
    void testRotateAngleStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("LEFT"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("LEFT ABC"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("LEFT 50 A"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIGHT"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIGHT 90 7"));
        assertThrows(InvalidCharactersException.class, () -> parser.parse("RIGHT -10"));
    }
}
