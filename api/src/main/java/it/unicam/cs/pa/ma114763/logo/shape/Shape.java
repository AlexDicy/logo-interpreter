package it.unicam.cs.pa.ma114763.logo.shape;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;

/**
 * Represents any shape that can be drawn on a canvas, such as a line or a polygon.
 * <p>
 * The Shape drawing logic in the shape itself, this allows to add more shapes without
 * having to update subclasses of {@link DrawingContext}.
 *
 * @author Lorenzo Lapucci
 */
public interface Shape {

    /**
     * Draws the shape on the canvas.
     *
     * @param ctx the drawing context
     */
    void draw(DrawingContext ctx);

    /**
     * Converts the {@link Shape} object into a string that represents the shape.
     *
     * @return the serialized string
     */
    String serialize();
}
