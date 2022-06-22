package it.unicam.cs.pa.ma114763.logo.shape;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.Position2D;

/**
 * @param start the start position of the line
 * @param end   the end position of the line
 * @param color the color of the line stroke
 * @param size  the size of the line stroke
 * @author Lorenzo Lapucci
 */
public record Line(Position2D start, Position2D end, int size, Color color) implements Shape {
    @Override
    public void draw(DrawingContext ctx) {
        ctx.setStrokeSize(size);
        ctx.setStrokeColor(color);
        ctx.getDrawingCanvas().strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
