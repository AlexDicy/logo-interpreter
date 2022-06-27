package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import javafx.scene.canvas.Canvas;

/**
 * @author Lorenzo Lapucci
 */
public class CanvasResizeHandler {

    private final Canvas canvas;
    private final DrawingContext drawing;

    public CanvasResizeHandler(Canvas canvas, DrawingContext drawing) {
        this.canvas = canvas;
        this.drawing = drawing;
    }

    public void resize(int width, int height) {
        canvas.resize(width, height);
        drawing.setWidth(width);
        drawing.setHeight(height);
    }

    public int getCurrentWidth() {
        return drawing.getWidth();
    }

    public int getCurrentHeight() {
        return drawing.getHeight();
    }
}
