package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.LogoDrawing;
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
public class FXDrawing extends LogoDrawing {
    private final GraphicsContext canvasCtx;

    public FXDrawing(Canvas canvas, int width, int height) {
        super(width, height);
        this.canvasCtx = canvas.getGraphicsContext2D();
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
        canvasCtx.strokeLine(startX, getHeight() - startY, endX, getHeight() - endY);
    }

    @Override
    public void clear() {
        super.clear();
        repaint();
    }

    private void repaint() {
        canvasCtx.setFill(Utils.toFXPaint(getBackgroundColor()));
        canvasCtx.fillRect(0, 0, getWidth(), getHeight());
        canvasCtx.setFill(Utils.toFXPaint(getFillColor()));
        List<Shape> shapes = getShapes();
        for (Shape shape : shapes) {
            shape.draw(this);
        }
    }
}
