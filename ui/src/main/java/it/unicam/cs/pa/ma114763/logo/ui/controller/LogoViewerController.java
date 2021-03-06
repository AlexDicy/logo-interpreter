package it.unicam.cs.pa.ma114763.logo.ui.controller;

import it.unicam.cs.pa.ma114763.logo.LogoInterpreter;
import it.unicam.cs.pa.ma114763.logo.io.FileResourceWriter;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.SingleParseResult;
import it.unicam.cs.pa.ma114763.logo.parser.Token;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.NumberTokenType;
import it.unicam.cs.pa.ma114763.logo.parser.tokentype.WordTokenType;
import it.unicam.cs.pa.ma114763.logo.processor.LogoProcessor;
import it.unicam.cs.pa.ma114763.logo.statement.Statement;
import it.unicam.cs.pa.ma114763.logo.ui.CanvasResizeHandler;
import it.unicam.cs.pa.ma114763.logo.ui.FXDrawing;
import it.unicam.cs.pa.ma114763.logo.ui.LogoUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoViewerController implements DataController<String> {
    private String program;
    private List<SingleParseResult> parseResults;
    private LogoParser parser;
    private LogoProcessor processor;
    private LogoInterpreter interpreter;
    private FXDrawing drawing;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField commandInput;

    @FXML
    private TextFlow commandsList;

    @Override
    public void setData(String data) {
        this.program = data;
        initializeInterpreter();
        fillCommandsList();
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
            parseResults = interpreter.initialize(parser, program);
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
    private void goBack() {
        LogoUI.getInstance().openRoot("fxml/choose_file.fxml");
    }

    @FXML
    private void reset() {
        drawing.clear();
        drawing.home();
        drawing.setCurrentDirection(0);
        interpreter.reset();
        fillCommandsList();
    }

    @FXML
    private void resetAndRunAll() {
        reset();
        interpreter.runAll();
    }

    @FXML
    private void runNextLine() {
        interpreter.runNextRoot();
        fillCommandsList();
    }

    @FXML
    private void changeDrawingSize() {
        CanvasResizeHandler resizeHandler = new CanvasResizeHandler(canvas, drawing);
        LogoUI.getInstance().openRoot("fxml/change_drawing_size.fxml", resizeHandler, true);
    }

    @FXML
    private void saveToFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Logo File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Logo file", "*.logo"), new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            FileResourceWriter writer = new FileResourceWriter(file);
            try {
                writer.write(drawing.getDrawingAsString());
                new Alert(Alert.AlertType.INFORMATION, "File saved successfully").show();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Error while saving file\n\nError: " + e.getMessage()).show();
            }
        }
    }

    private void fillCommandsList() {
        commandsList.getChildren().clear();
        for (SingleParseResult result : parseResults) {
            boolean shouldHighlight = result.index() == interpreter.getLastRanRootIndex();
            for (Token token : result.tokens()) {
                commandsList.getChildren().addAll(getTextForToken(token, shouldHighlight), new Text(" "));
            }
            if (commandsList.getChildren().size() > 0) {
                Text last = (Text) commandsList.getChildren().get(commandsList.getChildren().size() - 1);
                last.setText("\n");
            }
        }
    }

    private Text getTextForToken(Token result, boolean highlight) {
        Text line = new Text();
        switch (result.type()) {
            case WordTokenType t -> line.setStyle("-fx-fill: #20A3D6;-fx-font-weight:bold");
            case NumberTokenType t -> line.setStyle("-fx-fill: #61e5e5");
            default -> line.setStyle("-fx-fill: white");
        }
        if (highlight) {
            line.setStyle(line.getStyle() + ";-fx-fill: white");
        }
        line.setText(result.text());
        return line;
    }
}
