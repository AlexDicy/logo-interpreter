package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.shape.Line;
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


    private Position2D position;
    double direction;

    private int strokeSize = 1;
    private Color stokeColor = new RGBColor(0, 0, 0);
    private Color fillColor = new RGBColor(255, 255, 255);
    private Color backgroundColor = new RGBColor(255, 255, 255);

    private boolean isDrawing = true;

    private final List<Shape> shapes = new ArrayList<>();
    /**
     * Hold the current shape being drawn.
     * If null, no shape is being drawn.
     * <p>
     * Used to store drawn lines to later decide if the shape is closed.
     */
    private List<Line> currentShape = null;

    public LogoDrawing(int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Point(width / 2.0, height / 2.0);
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
    public Position2D getCurrentPosition() {
        return position;
    }

    @Override
    public void setCurrentPosition(int x, int y) {
        position = new Point(x, y);
    }

    @Override
    public double getCurrentDirection() {
        return direction;
    }

    @Override
    public void setCurrentDirection(double angle) {
        direction = angle;
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
        if (currentShape == null) {
            currentShape = new ArrayList<>();
        }
        Line line = new Line(start, end);
        currentShape.add(line);
        drawLine(line);
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

    protected abstract void drawLine(Line line);

    protected Position2D getSafePoint(double x, double y) {
        return new Point(Math.max(0, Math.min(x, width)), Math.max(0, Math.min(y, height)));
    }
}
