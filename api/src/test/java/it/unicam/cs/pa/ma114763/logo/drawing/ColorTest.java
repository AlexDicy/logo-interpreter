package it.unicam.cs.pa.ma114763.logo.drawing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test uses the {@link RGBColor} implementation to test the {@link Color}
 *
 * @author Lorenzo Lapucci
 */
class ColorTest {

    @Test
    void testValues() {
        Color c1 = new RGBColor(0, 0, 0, 0);
        assertColorValues(0, 0, 0, 0, c1);
        Color c2 = new RGBColor(255, 255, 255, 255);
        assertColorValues(255, 255, 255, 255, c2);
        Color c3 = new RGBColor(128, 128, 128, 128);
        assertColorValues(128, 128, 128, 128, c3);
        Color c4 = new RGBColor(10, 27, 45);
        assertColorValues(10, 27, 45, 255, c4);
    }

    @Test
    void testInvalidValues() {
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(-1, 0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(0, -1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(0, 0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(0, 0, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(256, 0, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(0, 256, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(0, 0, 256, 0));
        assertThrows(IllegalArgumentException.class, () -> new RGBColor(0, 0, 0, 256));
    }

    @Test
    void testOpacity() {
        Color c1 = new RGBColor(0, 0, 0);
        assertTrue(c1.isOpaque());
        Color c2 = new RGBColor(0, 0, 0, 255);
        assertTrue(c2.isOpaque());
        Color c3 = new RGBColor(0, 0, 0, 0);
        assertFalse(c3.isOpaque());
        Color c4 = new RGBColor(0, 0, 0, 1);
        assertFalse(c4.isOpaque());
        Color c5 = new RGBColor(0, 0, 0, 254);
        assertFalse(c5.isOpaque());
    }

    private static void assertColorValues(int r, int g, int b, int a, Color color) {
        assertEquals(r, color.getRed());
        assertEquals(g, color.getGreen());
        assertEquals(b, color.getBlue());
        assertEquals(a, color.getAlpha());
    }
}
