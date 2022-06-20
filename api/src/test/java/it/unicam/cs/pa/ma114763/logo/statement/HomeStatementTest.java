package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;
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
class HomeStatementTest {
    @Test
    void testHomeStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("HOME");
        assertEquals(1, statements.size());
        assertInstanceOf(HomeStatement.class, statements.get(0));

        assertThrows(UnknownCommandException.class, () -> parser.parse("HOMES"));
        assertThrows(UnknownCommandException.class, () -> parser.parse("HOUSE"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("HOME b"));
    }
}
