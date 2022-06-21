package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.LogoDrawing;
import it.unicam.cs.pa.ma114763.logo.shape.Line;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
    protected void drawLine(Line line) {
        // set all the properties
        canvasCtx.setStroke(Utils.toFXPaint(getStrokeColor()));
        canvasCtx.setFill(Utils.toFXPaint(getFillColor()));
        canvasCtx.setLineWidth(getStrokeSize());
        // convert the coordinates to the system used by the canvas
        double startX = line.start().getX();
        double startY = getHeight() - line.start().getY();
        double endX = line.end().getX();
        double endY = getHeight() - line.end().getY();
        // draw the line
        canvasCtx.strokeLine(startX, startY, endX, endY);
    }

    @Override
    public void clear() {
        super.clear();
        canvasCtx.setFill(Utils.toFXPaint(getBackgroundColor()));
        canvasCtx.fillRect(0, 0, getWidth(), getHeight());
        canvasCtx.setFill(Utils.toFXPaint(getFillColor()));
    }
}
