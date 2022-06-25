package it.unicam.cs.pa.ma114763.logo.processor;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.drawing.DummyLogoDrawing;
import it.unicam.cs.pa.ma114763.logo.statement.HomeStatement;
import it.unicam.cs.pa.ma114763.logo.statement.MoveStatement;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Lorenzo Lapucci
 */
class LogoProcessorTest {

    @Test
    void testExecute() {
        Processor processor = new LogoProcessor();
        DrawingContext drawing = new DummyLogoDrawing(100, 100);
        List<?> result = processor.execute(new MoveStatement(10, false), drawing);
        assertEquals(60, drawing.getCurrentPosition().getX());
        assertNull(result);
        result = processor.execute(new HomeStatement(), drawing);
        assertEquals(50, drawing.getCurrentPosition().getX());
        assertNull(result);
    }
}
