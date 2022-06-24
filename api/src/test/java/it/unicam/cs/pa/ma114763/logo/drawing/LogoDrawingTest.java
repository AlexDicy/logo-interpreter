package it.unicam.cs.pa.ma114763.logo.drawing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lorenzo Lapucci
 */
class LogoDrawingTest {

    @Test
    void testHomePosition() {
        DrawingContext drawing = new DummyLogoDrawing(100, 100);
        assertTrue(drawing.getCurrentPosition().isSamePosition(new Point(50, 50)));
    }

    @Test
    void testCurrentDirection() {
        DrawingContext drawing = new DummyLogoDrawing(200, 300);
        assertEquals(0, drawing.getCurrentDirection());
        drawing.setCurrentDirection(90);
        assertEquals(90, drawing.getCurrentDirection());
    }

    @Test
    void testRotate() {
        DrawingContext drawing = new DummyLogoDrawing(5, 5);
        drawing.setCurrentDirection(90);
        drawing.rotate(90);
        assertEquals(180, drawing.getCurrentDirection());
        drawing.rotate(90);
        assertEquals(270, drawing.getCurrentDirection());
        drawing.rotate(90);
        assertEquals(0, drawing.getCurrentDirection());
        drawing.rotate(90);
        assertEquals(90, drawing.getCurrentDirection());
    }

    @Test
    void testMoveTo() {
        DrawingContext drawing = new DummyLogoDrawing(5, 5);
        drawing.moveTo(0, 0);
        assertTrue(drawing.getCurrentPosition().isSamePosition(new Point(0, 0)));
        assertEquals(1, drawing.getShapes().size());
        drawing.moveTo(1, 1);
        assertTrue(drawing.getCurrentPosition().isSamePosition(new Point(1, 1)));
        assertEquals(2, drawing.getShapes().size());
        drawing.setDrawing(false);
        drawing.moveTo(2, 2);
        assertTrue(drawing.getCurrentPosition().isSamePosition(new Point(2, 2)));
        assertEquals(2, drawing.getShapes().size());
    }

    @Test
    void testGetShapes() {
        DrawingContext drawing = new DummyLogoDrawing(5, 5);
        drawing.setCurrentPosition(0, 0);
        drawing.moveTo(1, 1);
        drawing.moveTo(2, 2);
        drawing.moveTo(0, 2);
        assertEquals(3, drawing.getShapes().size());
        drawing.moveTo(0, 0);
        assertEquals(1, drawing.getShapes().size());
    }

    @Test
    void testClear() {
        DrawingContext drawing = new DummyLogoDrawing(5, 5);
        drawing.moveTo(1, 1);
        assertEquals(1, drawing.getShapes().size());
        drawing.clear();
        assertEquals(0, drawing.getShapes().size());
    }

    @Test
    void testGetSafePoint() {
        DrawingContext drawing = new DummyLogoDrawing(5, 5);
        drawing.setCurrentPosition(-10, 80);
        assertTrue(drawing.getCurrentPosition().isSamePosition(new Point(0, 5)));
        drawing.moveTo(20, -20);
        assertTrue(drawing.getCurrentPosition().isSamePosition(new Point(5, 0)));
    }

    static class DummyLogoDrawing extends LogoDrawing {
        public DummyLogoDrawing(int width, int height) {
            super(width, height);
        }

        @Override
        public DrawingCanvas getDrawingCanvas() {
            return new DrawingCanvas() {
                @Override
                public void strokeLine(double startX, double startY, double endX, double endY) {
                }

                @Override
                public void fillPolygon(List<Position2D> points) {
                }
            };
        }
    }
}
