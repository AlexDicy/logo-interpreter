package it.unicam.cs.pa.ma114763.logo.ui.controller;

import it.unicam.cs.pa.ma114763.logo.drawing.Color;
import it.unicam.cs.pa.ma114763.logo.drawing.RGBColor;
import it.unicam.cs.pa.ma114763.logo.shape.Shape;
import it.unicam.cs.pa.ma114763.logo.ui.FXDrawing;
import it.unicam.cs.pa.ma114763.logo.ui.LogoReader;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class OutputViewerController implements DataController<String> {

    @FXML
    private Canvas canvas;

    @Override
    public void setData(String data) {
        parseHeaderAndGetDrawing(data);

        LogoReader reader = new LogoReader();
        List<Shape> shapes = reader.read(data);
        FXDrawing drawing = parseHeaderAndGetDrawing(data);
        canvas.resize(drawing.getWidth(), drawing.getHeight());
        drawing.repaint();
        for (Shape shape : shapes) {
            shape.draw(drawing);
        }
    }

    private FXDrawing parseHeaderAndGetDrawing(String data) {
        try {
            String[] header = data.split("\n")[0].split(" +");
            if (header.length != 6 && header.length != 7 && !header[0].equalsIgnoreCase("SIZE")) {
                throw new IllegalArgumentException("Invalid file header");
            }
            int width = Integer.parseInt(header[1]);
            int height = Integer.parseInt(header[2]);
            int r = Integer.parseInt(header[3]);
            int g = Integer.parseInt(header[4]);
            int b = Integer.parseInt(header[5]);
            int a = header.length == 7 ? Integer.parseInt(header[6]) : 255;
            Color bgColor = new RGBColor(r, g, b, a);
            FXDrawing drawing = new FXDrawing(canvas, width, height);
            drawing.setBackgroundColor(bgColor);
            return drawing;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid file header");
        }
    }
}
