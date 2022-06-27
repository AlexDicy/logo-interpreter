package it.unicam.cs.pa.ma114763.logo.ui.controller;

import it.unicam.cs.pa.ma114763.logo.LogoInterpreter;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.processor.LogoProcessor;
import it.unicam.cs.pa.ma114763.logo.statement.Statement;
import it.unicam.cs.pa.ma114763.logo.ui.FXDrawing;
import it.unicam.cs.pa.ma114763.logo.ui.controller.DataController.DataController;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoViewerController implements DataController<String> {
    private String program;
    private LogoParser parser;
    private LogoProcessor processor;
    private LogoInterpreter interpreter;
    private FXDrawing drawing;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField commandInput;

    @Override
    public void setData(String data) {
        this.program = data;
        initializeInterpreter();
    }

    @FXML
    private void initializeInterpreter() {
        drawing = new FXDrawing(canvas, 800, 600);
        canvas.resize(drawing.getWidth(), drawing.getHeight());
        drawing.repaint();

        parser = new LogoParser();
        processor = new LogoProcessor();
        interpreter = new LogoInterpreter(processor, drawing);
        try {
            interpreter.initialize(parser, program);
            interpreter.runAll();
        } catch (ParserException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    private void onCommandSend(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String command = commandInput.getText();
            try {
                List<Statement> statements = parser.parse(command);
                commandInput.setText("");
                processor.execute(statements, drawing);
            } catch (ParserException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    private void reset() {
        drawing.clear();
        drawing.home();
        drawing.setCurrentDirection(0);
        interpreter.resetQueue();
    }

    @FXML
    private void resetAndRunAll() {
        reset();
        interpreter.runAll();
    }

    @FXML
    private void runNext() {
        interpreter.runNext();
    }
}