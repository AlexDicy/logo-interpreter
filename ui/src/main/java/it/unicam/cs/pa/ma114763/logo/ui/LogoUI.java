package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.LogoProcessor;
import it.unicam.cs.pa.ma114763.logo.RGBColor;
import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.shape.Shape;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoUI extends Application {
    @Override
    public void start(Stage stage) throws ParserException {
        int width = 800;
        int height = 600;

        String program = "setpencolor 121 153 237\nrepeat 18 [repeat 5 [rt 40 fd 200 rt 120] rt 20]";

        Canvas canvas = new Canvas(width, height);
        Scene scene = new Scene(new StackPane(canvas), width, height);
        stage.setScene(scene);
        stage.show();

        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse(program);
        LogoProcessor processor = new LogoProcessor();
        FXDrawing drawing = new FXDrawing(canvas, width, height);
        processor.execute(statements, drawing);

        canvas.setOnMouseClicked(event -> {
            drawing.setBackgroundColor(new RGBColor(0, 0, 0));
            drawing.setStrokeColor(new RGBColor(255, 255, 255));

            List<Shape> shapes = drawing.getShapes();
            for (Shape shape : shapes) {
                shape.draw(drawing);
            }
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
