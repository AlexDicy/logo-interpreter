package it.unicam.cs.pa.ma114763.logo.ui.controller;

import it.unicam.cs.pa.ma114763.logo.LogoInterpreter;
import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.processor.LogoProcessor;
import it.unicam.cs.pa.ma114763.logo.ui.FXDrawing;
import it.unicam.cs.pa.ma114763.logo.ui.controller.DataController.DataController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;

/**
 * @author Lorenzo Lapucci
 */
public class LogoViewerController implements DataController<String> {
    private String program;
    private LogoInterpreter interpreter;

    @FXML
    private Canvas canvas;

    @Override
    public void setData(String data) {
        this.program = data;
        initializeInterpreter();
    }

    @FXML
    private void initializeInterpreter() {
        DrawingContext drawing = new FXDrawing(canvas, 800, 600);
        canvas.resize(drawing.getWidth(), drawing.getHeight());
        interpreter = new LogoInterpreter(new LogoProcessor(), drawing);
        try {
            interpreter.initialize(new LogoParser(), program);
            interpreter.runAll();
        } catch (ParserException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
