package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.shape.Shape;

import java.util.List;

/**
 * A Canvas holds information about the drawing, such as size, current position,
 * current color, shapes, and the background color.
 *
 * @author Lorenzo Lapucci
 */
public interface DrawingContext {
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
     * Sets the current position of the cursor.
     *
     * @param x the x coordinate of the new position
     * @param y the y coordinate of the new position
     */
    void setCurrentPosition(int x, int y);

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
     * Sets the current direction in degrees.
     *
     * @param angle the new direction in degrees
     */
    void setCurrentDirection(double angle);

    /**
     * Rotates the current direction by the given angle in degrees.
     *
     * @param angle the angle in degrees to rotate the direction
     */
    void rotate(double angle);

    /**
     * @return the current stroke size
     */
    int getStrokeSize();

    /**
     * Sets the size of the strokes.
     *
     * @param size the new size of the strokes
     */
    void setStrokeSize(int size);

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
     * Moves the cursor to the given position.
     * Draws a line if {@link #isDrawing()} is true.
     *
     * @param x the x coordinate of the new position
     * @param y the y coordinate of the new position
     */
    void moveTo(int x, int y);

    /**
     * Moves the cursor by the given distance.
     * Draws a line if {@link #isDrawing()} is true.
     *
     * @param offsetX the distance to move in the x direction
     * @param offsetY the distance to move in the y direction
     */
    default void move(int offsetX, int offsetY) {
        moveTo(getCurrentPosition().getX() + offsetX, getCurrentPosition().getY() + offsetY);
    }

    /**
     * Returns a list of all shapes on the canvas, ordered by their appearance.
     *
     * @return every shape currently drawn on the canvas
     */
    List<Shape> getShapes();

    /**
     * Clears the canvas using the background color.
     */
    void clear();
}
