package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.LogoDrawing;
import it.unicam.cs.pa.ma114763.logo.shape.Line;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
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
        // draw the line
        canvasCtx.strokeLine(line.start().getX(), line.start().getY(), line.end().getX(), line.end().getY());
    }

    @Override
    public void clear() {
        super.clear();
        canvasCtx.setFill(Utils.toFXPaint(getBackgroundColor()));
        canvasCtx.fillRect(0, 0, getWidth(), getHeight());
        canvasCtx.setFill(Utils.toFXPaint(getFillColor()));
    }
}
