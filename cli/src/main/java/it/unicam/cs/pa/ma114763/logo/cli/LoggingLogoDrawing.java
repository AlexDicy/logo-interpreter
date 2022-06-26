package it.unicam.cs.pa.ma114763.logo.cli;

import it.unicam.cs.pa.ma114763.logo.drawing.Color;
import it.unicam.cs.pa.ma114763.logo.drawing.DrawingCanvas;
import it.unicam.cs.pa.ma114763.logo.drawing.LogoDrawing;
import it.unicam.cs.pa.ma114763.logo.drawing.Position2D;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LoggingLogoDrawing extends LogoDrawing {
    private final DrawingCanvas canvas;

    public LoggingLogoDrawing(int width, int height) {
        super(width, height);
        canvas = new LoggingDrawingCanvas();
    }

    @Override
    public DrawingCanvas getDrawingCanvas() {
        return canvas;
    }

    private class LoggingDrawingCanvas implements DrawingCanvas {

        @Override
        public void strokeLine(double startX, double startY, double endX, double endY) {
            System.out.println("Line drawn from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ") with the color values:");
            Color color = getStrokeColor();
            System.out.print("\tR: " + color.getRed() + ", G: " + color.getGreen() + ", B: " + color.getBlue() + ", A: " + color.getAlpha());
        }

        @Override
        public void fillPolygon(List<Position2D> points) {
            System.out.println("Polygon filled with " + points.size() + " points with the color values:");
            Color color = getFillColor();
            System.out.print("\tR: " + color.getRed() + ", G: " + color.getGreen() + ", B: " + color.getBlue() + ", A: " + color.getAlpha());
        }
    }
}
