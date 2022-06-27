package it.unicam.cs.pa.ma114763.logo.drawing;

import it.unicam.cs.pa.ma114763.logo.shape.Line;
import it.unicam.cs.pa.ma114763.logo.shape.Polygon;
import it.unicam.cs.pa.ma114763.logo.shape.Shape;
import org.jetbrains.annotations.MustBeInvokedByOverriders;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public abstract class LogoDrawing implements DrawingContext {
    private int width;
    private int height;


    /**
     * Holds the current position in the drawing
     * A position of <code>(0, 0)</code> is the bottom-left corner of the drawing
     */
    private Position2D position;
    double direction;

    private int strokeSize = 1;
    private Color stokeColor = new RGBColor(0, 0, 0);
    private Color fillColor = new RGBColor(255, 255, 255);
    private Color backgroundColor = new RGBColor(255, 255, 255);

    private boolean isDrawing = true;

    private final List<Shape> shapes = new ArrayList<>();
    /**
     * Holds the current shape being drawn.
     * If null, no shape is being drawn.
     * <p>
     * Used to store drawn lines to later decide if the shape is closed.
     */
    private List<Line> currentShape = null;

    public LogoDrawing(int width, int height) {
        this.width = width;
        this.height = height;
        home();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public Position2D getCurrentPosition() {
        return position;
    }

    @Override
    public void setCurrentPosition(double x, double y) {
        position = new Point(getSafeX(x), getSafeY(y));
    }

    @Override
    public double getCurrentDirection() {
        return direction;
    }

    @Override
    public void setCurrentDirection(double angle) {
        direction = angle;
    }

    /**
     * Sets the current position to the home position (center of the drawing)
     */
    public void home() {
        position = new Point(width / 2.0, height / 2.0);
    }

    @Override
    public void rotate(double angle) {
        direction += angle;
        direction %= 360;
    }

    @Override
    public int getStrokeSize() {
        return strokeSize;
    }

    @Override
    public void setStrokeSize(int size) {
        strokeSize = size;
    }

    @Override
    public Color getStrokeColor() {
        return stokeColor;
    }

    @Override
    public void setStrokeColor(Color color) {
        stokeColor = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Color color) {
        fillColor = color;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    @Override
    public boolean isDrawing() {
        return isDrawing;
    }

    @Override
    public void setDrawing(boolean drawing) {
        isDrawing = drawing;
    }

    @Override
    public void moveTo(double x, double y) {
        Position2D newPosition = getSafePoint(x, y);
        if (isDrawing) {
            addLine(position, newPosition);
        }
        position = newPosition;
    }

    private void addLine(Position2D start, Position2D end) {
        // start a new shape if no shape is being drawn or if the last index added is not part of the shape
        if (currentShape == null || currentShape.size() > 0 && !currentShape.get(currentShape.size() - 1).end().isSamePosition(start)) {
            currentShape = new ArrayList<>();
        }
        Line line = new Line(start, end, strokeSize, stokeColor);
        currentShape.add(line);
        shapes.add(line);
        analyzeClosedShape();
        getDrawingCanvas().strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
    }

    /**
     * If the last index added to the {@link #currentShape} ends
     * at the start of the first index, it means that the shape is closed.
     * <p>
     * If the shape is closed, its lines are replaced in the {@link #shapes} list
     * with a {@link Polygon} and the {@link #currentShape} is set to null.
     */
    private void analyzeClosedShape() {
        if (currentShape.size() > 2) {
            Line firstLine = currentShape.get(0);
            Line lastLine = currentShape.get(currentShape.size() - 1);
            if (lastLine.end().isSamePosition(firstLine.start())) {
                Polygon polygon = new Polygon(fillColor, currentShape);
                shapes.subList(shapes.size() - currentShape.size(), shapes.size()).clear(); // changes in the sublist are reflected in the original list
                shapes.add(polygon);
                currentShape = null;
                polygon.draw(this);
            }
        }
    }

    @Override
    public List<Shape> getShapes() {
        return shapes;
    }

    @Override
    @MustBeInvokedByOverriders
    public void clear() {
        shapes.clear();
        currentShape = null;
    }

    protected double getSafeX(double x) {
        return Math.max(0, Math.min(x, width));
    }

    protected double getSafeY(double y) {
        return Math.max(0, Math.min(y, height));
    }

    protected Position2D getSafePoint(double x, double y) {
        return new Point(getSafeX(x), getSafeY(y));
    }
}
