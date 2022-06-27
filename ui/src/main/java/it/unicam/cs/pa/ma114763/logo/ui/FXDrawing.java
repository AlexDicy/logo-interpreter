package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.drawing.*;
import it.unicam.cs.pa.ma114763.logo.shape.Shape;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

/**
 * Wrapper for the JavaFX Canvas.
 * {@link DrawingContext} uses (x: 0, y: 0) as the bottom left corner of the drawing
 * {@link FXDrawing} takes that into account and converts the coordinates to the system used by the canvas,
 * which is (x: 0, y: 0) as the top left corner.
 *
 * @author Lorenzo Lapucci
 */
public class FXDrawing extends LogoDrawing implements DrawingCanvas {
    private final GraphicsContext canvasCtx;

    public FXDrawing(Canvas canvas, int width, int height) {
        super(width, height);
        this.canvasCtx = canvas.getGraphicsContext2D();
        repaint();
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        repaint();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        repaint();
    }

    @Override
    public void setBackgroundColor(Color color) {
        super.setBackgroundColor(color);
        repaint();
    }

    @Override
    public void strokeLine(double startX, double startY, double endX, double endY) {
        canvasCtx.setStroke(Utils.toFXPaint(getStrokeColor()));
        canvasCtx.setLineWidth(getStrokeSize());
        // convert the coordinates to the system used by the canvas and draw the line
        canvasCtx.strokeLine(getSafeX(startX), getHeight() - getSafeY(startY), getSafeX(endX), getHeight() - getSafeY(endY));
    }

    @Override
    public void fillPolygon(List<Position2D> points) {
        canvasCtx.setFill(Utils.toFXPaint(getFillColor()));
        double[] xPoints = points.stream().mapToDouble(p -> getSafeX(p.getX())).toArray();
        double[] yPoints = points.stream().mapToDouble(p -> getHeight() - getSafeY(p.getY())).toArray();
        canvasCtx.fillPolygon(xPoints, yPoints, points.size());
    }

    @Override
    public void clear() {
        super.clear();
        repaint();
    }

    @Override
    public DrawingCanvas getDrawingCanvas() {
        return this;
    }

    public void repaint() {
        canvasCtx.setFill(Utils.toFXPaint(getBackgroundColor()));
        canvasCtx.fillRect(0, 0, getWidth(), getHeight());
        canvasCtx.setFill(Utils.toFXPaint(getFillColor()));
        List<Shape> shapes = getShapes();
        for (Shape shape : shapes) {
            shape.draw(this);
        }
    }
}
