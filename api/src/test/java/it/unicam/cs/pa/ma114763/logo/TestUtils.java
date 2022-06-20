package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.statement.SetBackgroundColorStatement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {
    public static void assertColorStatement(SetBackgroundColorStatement statement, byte r, byte g, byte b, byte a) {
        Color c = statement.color();
        assertEquals(r, c.getRed());
        assertEquals(g, c.getGreen());
        assertEquals(b, c.getBlue());
        assertEquals(a, c.getAlpha());
    }
}
