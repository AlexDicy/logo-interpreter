package it.unicam.cs.pa.ma114763.logo.drawing;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class DummyLogoDrawing extends LogoDrawing {
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
