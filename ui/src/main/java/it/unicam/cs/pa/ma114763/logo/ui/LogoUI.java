package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.LogoProcessor;
import it.unicam.cs.pa.ma114763.logo.Statement;
import it.unicam.cs.pa.ma114763.logo.parser.LogoParser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
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
        String program = """
                PENDOWN
                FORWARD 10
                PENUP
                FORWARD 20
                PENDOWN
                FORWARD 10
                RIGHT 90
                FORWARD 40
                LEFT 315
                SETPENCOLOR 255 127 0
                BACKWARD 40
                FORWARD 80
                HOME
                FORWARD 20
                SETPENCOLOR 0 127 255 127
                
                
                ripeti 8 [right 45 ripeti 6 [ripeti 90 [forward 2 right 2] right 90]]
                """;

        Canvas canvas = new Canvas(640, 480);
        Scene scene = new Scene(new StackPane(canvas), 640, 480);
        stage.setScene(scene);
        stage.show();

        LogoParser parser = new LogoParser();
        List<Statement> statements = parser.parse(program);
        LogoProcessor processor = new LogoProcessor();
        processor.execute(statements, new FXDrawing(canvas, 640, 480));

        canvas.setOnMouseClicked(e -> {
            canvas.getGraphicsContext2D().strokeLine(0, 0, e.getX(), e.getY());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
