package it.unicam.cs.pa.ma114763.logo.ui;

import javafx.scene.canvas.Canvas;

/**
 * @author Lorenzo Lapucci
 */
public class ResizableCanvas extends Canvas {

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }
}
