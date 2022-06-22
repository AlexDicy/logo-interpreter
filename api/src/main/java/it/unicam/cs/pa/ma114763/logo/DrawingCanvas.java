package it.unicam.cs.pa.ma114763.logo;

import java.util.List;

/**
 * This class is used to issue draw calls to a generic canvas.
 *
 * @author Lorenzo Lapucci
 */
public interface DrawingCanvas {

    /**
     * Draws a line from the current position to the given position.
     *
     * @param startX the x coordinate of the starting point
     * @param startY the y coordinate of the starting point
     * @param endX   the x coordinate of the ending point
     * @param endY   the y coordinate of the ending point
     */
    void strokeLine(double startX, double startY, double endX, double endY);

    /**
     * Draws a filled polygon.
     * The polygon is defined by the given list of points.
     *
     * @param points the list of points that define the polygon
     */
    void fillPolygon(List<Position2D> points);
}
