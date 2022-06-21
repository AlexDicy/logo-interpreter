package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.shape.Line;
import it.unicam.cs.pa.ma114763.logo.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public abstract class LogoDrawing implements DrawingContext {
    private int width;
    private int height;


    private Position2D position = new Point(0, 0);
    double direction;

    private Color stokeColor = new RGBColor((byte) 0, (byte) 0, (byte) 0);
    private Color fillColor = new RGBColor((byte) 255, (byte) 255, (byte) 255);
    private Color backgroundColor = new RGBColor((byte) 255, (byte) 255, (byte) 255);

    private boolean isDrawing = false;

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
    public void moveTo(int x, int y) {
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
    public void clear() {
        shapes.clear();
        currentShape = null;
    }

    protected abstract void drawLine(Line line);

    protected Position2D getSafePoint(int x, int y) {
        return new Point(Math.max(0, Math.min(x, width)), Math.max(0, Math.min(y, height)));
    }
}
