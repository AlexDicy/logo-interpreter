package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.LogoInterpreter;
import it.unicam.cs.pa.ma114763.logo.LogoProcessor;
import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.drawing.Point;
import it.unicam.cs.pa.ma114763.logo.drawing.RGBColor;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.shape.Line;
import it.unicam.cs.pa.ma114763.logo.shape.Polygon;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lorenzo Lapucci
 */
public class LogoUI extends Application {
    @Override
    public void start(Stage stage) throws ParserException {
        final int width = 800;
        final int height = 600;
        final int textAreaHeight = 400;

        String program = """
                setpencolor 121 153 237
                setfillcolor 121 153 237 120
                repeat 18 [repeat 5 [rt 40 fd 200 rt 120] rt 20]
                home
                right 90
                penup
                backward 100
                pendown
                backward 100
                right 60
                forward 100
                left 120
                setfillcolor 237 153 121
                forward 100
                """;

        Canvas canvas = new Canvas(width, height);
        TextArea textArea = new TextArea();
        textArea.setPrefWidth(width);
        textArea.setPrefHeight(textAreaHeight);
        textArea.setWrapText(true);
        Scene scene = new Scene(new VBox(canvas, textArea), width, height + textAreaHeight);
        stage.setScene(scene);
        stage.show();

        DrawingContext drawing = new FXDrawing(canvas, width, height);
        LogoInterpreter interpreter = new LogoInterpreter(new LogoProcessor(), drawing);
        interpreter.initialize(new LogoParser(), program);
        //interpreter.runAll();

        textArea.setText(drawing.getDrawingAsString());
        textArea.setEditable(false);

        AtomicBoolean flag = new AtomicBoolean(false);

        canvas.setOnMouseClicked(event -> {
            if (flag.compareAndSet(false, true)) {
                drawing.setBackgroundColor(new RGBColor(0, 0, 0));
            } else {
                drawing.setBackgroundColor(new RGBColor(255, 0, 255));
                flag.set(false);
            }

            List<Line> strokes = List.of(
                    new Line(new Point(-200, 20), new Point(110, 210), 1, new RGBColor(255, 255, 255)),
                    new Line(new Point(110, 210), new Point(120, 220), 1, new RGBColor(255, 255, 255)),
                    new Line(new Point(120, 220), new Point(130, 230), 1, new RGBColor(255, 255, 255)),
                    new Line(new Point(130, 230), new Point(140, 240), 1, new RGBColor(255, 0, 0)),
                    new Line(new Point(140, 240), new Point(145, 245), 1, new RGBColor(0, 0, 255)),
                    new Line(new Point(145, 245), new Point(145, 200), 1, new RGBColor(255, 255, 255)),
                    new Line(new Point(145, 200), new Point(-200, 20), 1, new RGBColor(255, 255, 255))
            );
            Polygon polygon = new Polygon(new RGBColor(255, 255, 255, 127), strokes);
            polygon.draw(drawing);
            textArea.setText(drawing.getDrawingAsString());
        });

        textArea.setOnKeyPressed(event -> {
            if (event.getCode() != KeyCode.SPACE) {
                return;
            }
            interpreter.runNext();
        });

//        LinkedList<Statement> queue = new LinkedList<>(statements);
//
//        scene.setOnKeyPressed(event -> {
//            if (queue.isEmpty() || event.getCode() != KeyCode.SPACE) {
//                return;
//            }
//            // execute and add result to the head of the queue
//            Statement statement = queue.removeFirst();
//            System.out.println("Executing: " + statement);
//            List<Statement> result = processor.execute(statement, drawing);
//            if (result != null) {
//                // add results to the head of the queue
//                queue.addAll(0, result);
//            }
//        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
