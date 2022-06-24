package it.unicam.cs.pa.ma114763.logo.drawing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test uses the {@link Point} implementation to test the {@link Position2D}
 *
 * @author Lorenzo Lapucci
 */
class Position2DTest {

    @Test
    void testIsSamePosition() {
        Position2D p1 = new Point(0, 0);
        Position2D p2 = new Point(0, 0);
        assertTrue(p1.isSamePosition(p2));
        Position2D p3 = new Point(1, 1);
        assertFalse(p1.isSamePosition(p3));
        // test EPSILON
        Position2D p4 = new Point(2.7E-12, 0);
        assertTrue(p1.isSamePosition(p4));
        Position2D p5 = new Point(0, 1.3E-12);
        assertTrue(p1.isSamePosition(p5));
    }
}
