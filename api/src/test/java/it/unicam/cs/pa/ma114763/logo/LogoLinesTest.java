package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.drawing.DummyLogoDrawing;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.processor.LogoProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Lorenzo Lapucci
 */
public class LogoLinesTest {

    @Test
    void testLogoLines() throws ParserException {
        String program = """
                setscreencolor 1 2 3
                backward 100
                right 90
                forward 100
                setpencolor 237 153 121 45
                left 180
                forward 100
                """;
        DrawingContext drawing = new DummyLogoDrawing(1024, 512);
        LogoInterpreter interpreter = new LogoInterpreter(new LogoProcessor(), drawing);
        interpreter.initialize(new LogoParser(), program);
        assertEquals("SIZE 1024 512 255 255 255\n", drawing.getDrawingAsString());
        interpreter.runAll();
        assertEquals("""
                SIZE 1024 512 1 2 3
                LINE 512.0 256.0 412.0 256.0 0 0 0 1
                LINE 412.0 256.0 412.0 156.0 0 0 0 1
                LINE 412.0 156.0 412.0 256.0 237 153 121 45 1
                """, drawing.getDrawingAsString());
    }
}
