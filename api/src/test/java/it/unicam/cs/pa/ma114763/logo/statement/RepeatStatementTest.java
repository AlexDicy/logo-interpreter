package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.InvalidSyntaxException;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lorenzo Lapucci
 */
class RepeatStatementTest {

    @Test
    void testRepeatStatement() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("RIPETI 3 [PENUP PENDOWN LEFT 10 HOME]");
        assertEquals(1, statements.size());
        assertInstanceOf(RepeatStatement.class, statements.get(0));
        RepeatStatement repeatStatement = (RepeatStatement) statements.get(0);
        assertEquals(3, repeatStatement.times());
        List<Statement> innerStatements = repeatStatement.statements();
        assertEquals(4, innerStatements.size());
        assertInstanceOf(SetDrawingStatement.class, innerStatements.get(0));
        assertInstanceOf(SetDrawingStatement.class, innerStatements.get(1));
        assertInstanceOf(RotateAngleStatement.class, innerStatements.get(2));
        assertEquals(-10, ((RotateAngleStatement) innerStatements.get(2)).angleRotation());
        assertInstanceOf(HomeStatement.class, innerStatements.get(3));
    }

    @Test
    void testRepeatStatementExceptions() {
        LogoParser parser = new LogoParser();
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIPETI"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIPETI 3"));
        assertThrows(InvalidSyntaxException.class, () -> parser.parse("RIPETI 3 [PENUP PENDOWN LEFT 10 HOME] PENUP"));
    }

    @Test
    void testRepeatStatementWhitespace() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements1 = parser.parse("RIPETI 3 [HOME CLEARSCREEN]");
        assertEquals(1, statements1.size());
        RepeatStatement st1 = (RepeatStatement) statements1.get(0);
        assertEquals(2, st1.statements().size());

        List<Statement> statements2 = parser.parse("RIPETI 3 [ HOME   CLEARSCREEN ]");
        RepeatStatement st2 = (RepeatStatement) statements2.get(0);
        assertEquals(2, st2.statements().size());
        assertIterableEquals(st1.statements(), st2.statements());
    }

    @Test
    void testRepeatInsideRepeat() throws ParserException {
        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse("RIPETI 6 [RIPETI 3 [PENUP PENDOWN]]");
        assertEquals(1, statements.size());
        RepeatStatement repeat = (RepeatStatement) statements.get(0);
        assertEquals(1, repeat.statements().size());
        RepeatStatement innerRepeat = (RepeatStatement) repeat.statements().get(0);
        assertEquals(3, innerRepeat.times());
        assertEquals(2, innerRepeat.statements().size());
        statements = parser.parse("RIPETI 6 [RIPETI 3 [PENUP RIPETI 7 [PENUP PENDOWN] HOME]]");
        repeat = (RepeatStatement) statements.get(0);
        innerRepeat = (RepeatStatement) repeat.statements().get(0);
        RepeatStatement innerInnerRepeat = (RepeatStatement) innerRepeat.statements().get(1);
        assertEquals(7, innerInnerRepeat.times());
        assertEquals(2, innerInnerRepeat.statements().size());
    }
}
