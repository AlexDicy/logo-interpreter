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
public class LogoPolygonTest {

    @Test
    void testLogoPolygon() throws ParserException {
        String program = """
                setscreencolor 1 2 3
                backward 100
                right 60
                setpencolor 156 200 70 250
                forward 100
                left 120
                forward 100
                """;
        DrawingContext drawing = new DummyLogoDrawing(1024, 512);
        LogoInterpreter interpreter = new LogoInterpreter(new LogoProcessor(), drawing);
        interpreter.initialize(new LogoParser(), program);
        assertEquals("SIZE 1024 512 255 255 255\n", drawing.getDrawingAsString());
        interpreter.runAll();
        System.out.println(drawing.getDrawingAsString());
        assertEquals("""
                SIZE 1024 512 1 2 3
                POLYGON 3 255 255 255
                512.0 256.0 0 0 0 1
                412.0 256.0 156 200 70 250 1
                462.0 169.39745962155615 156 200 70 250 1
                """, drawing.getDrawingAsString());
    }
}
