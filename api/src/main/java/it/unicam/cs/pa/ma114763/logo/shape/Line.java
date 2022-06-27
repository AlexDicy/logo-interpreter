package it.unicam.cs.pa.ma114763.logo.shape;

import it.unicam.cs.pa.ma114763.logo.drawing.Color;
import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.drawing.Position2D;

/**
 * @param start the start position of the index
 * @param end   the end position of the index
 * @param color the color of the index stroke
 * @param size  the size of the index stroke
 * @author Lorenzo Lapucci
 */
public record Line(Position2D start, Position2D end, int size, Color color) implements Shape {
    @Override
    public void draw(DrawingContext ctx) {
        ctx.setStrokeSize(size);
        ctx.setStrokeColor(color);
        ctx.getDrawingCanvas().strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * Serializes the index into a command with the following format:
     * <p>
     * <code>LINE &lt;x<sub>1</sub>&gt; &lt;y<sub>1</sub>&gt; &lt;x<sub>2</sub>&gt; &lt;y<sub>2</sub>&gt; &lt;r&gt; &lt;g&gt; &lt;b&gt; [alpha] &lt;size&gt;</code>
     *
     * @return the serialized command
     */
    @Override
    public String serialize() {
        String command = "LINE " + start.getX() + " " + start.getY() + " " + end.getX() + " " + end.getY() + " " + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
        if (color.isOpaque()) {
            return command + " " + size;
        }
        return command + " " + color.getAlpha() + " " + size;
    }
}
