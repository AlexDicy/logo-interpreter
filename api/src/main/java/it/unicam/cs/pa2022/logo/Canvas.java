package it.unicam.cs.pa2022.logo;

import java.util.List;

/**
 * A Canvas holds information about the drawing, such as size, current position,
 * current color, shapes, and the background color.
 *
 * @author Lorenzo Lapucci
 */
public interface Canvas {
    /**
     * Returns the horizontal size of the canvas.
     *
     * @return the width of the canvas
     */
    int getWidth();

    /**
     * Returns the vertical size of the canvas.
     *
     * @return the height of the canvas
     */
    int getHeight();

    /**
     * @return the current position of the cursor
     */
    Position2D getCurrentPosition();

    /**
     * Returns the current direction represented as an angle in degrees.
     * The angle is measured clockwise from the positive x-axis.
     * An angle of 0 means the direction is to the right.
     *
     * <pre>
     *        [270]
     *          |
     * [180] ---|--- [  0]
     *          |
     *        [ 90]
     * </pre>
     *
     * @return the current direction of the cursor in degrees
     */
    double getCurrentDirection();

    /**
     * @return the current stroke color
     */
    Color getStrokeColor();

    /**
     * Sets the color used to draw the next shape.
     */
    void setStrokeColor(Color color);

    /**
     * @return the current fill color
     */
    Color getFillColor();

    /**
     * Sets the color used to fill the next closed shape.
     */
    void setFillColor(Color color);

    /**
     * @return the background color
     */
    Color getBackgroundColor();

    /**
     * Sets the background color of the canvas.
     */
    void setBackgroundColor(Color color);

    /**
     * @return true if moving the cursor should draw
     */
    boolean isDrawing();

    /**
     * Sets the drawing mode of the canvas.
     * If true, the cursor will draw when moving.
     * If false, the cursor will not draw.
     */
    void setDrawing(boolean drawing);

    /**
     * Returns a list of all shapes on the canvas, ordered by their appearance.
     *
     * @return every shape currently drawn on the canvas
     */
    List<Shape> getShapes();
}
