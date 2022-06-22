package it.unicam.cs.pa.ma114763.logo.shape;

import it.unicam.cs.pa.ma114763.logo.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.Position2D;

/**
 * @author Lorenzo Lapucci
 */
public record Line(Position2D start, Position2D end) implements Shape {
    @Override
    public void draw(DrawingContext ctx) {
        ctx.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }
}
