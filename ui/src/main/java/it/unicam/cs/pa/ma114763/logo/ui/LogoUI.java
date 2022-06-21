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
                FORWARD 130
                RIGHT 148
                FORWARD 153
                RIGHT 122
                FORWARD 80
                right 90
                
                SETPENCOLOR 255 127 0 127
                HOME
                PENUP
                RIGHT 180
                FORWARD 50
                RIGHT 90
                FORWARD 80
                LEFT 270
                PENDOWN
                
                RIPETI 36[RIPETI 90 [FORWARD 5 RIGHT 4] RIGHT 10]

                SETPENCOLOR 0 127 255 127
                HOME
                PENUP
                RIGHT 0
                FORWARD 140
                LEFT 0
                PENDOWN
                
                RIPETI 8 [right 45 RIPETI 6 [RIPETI 90 [FORWARD 2 RIGHT 2] RIGHT 90]]
                
                PENUP
                HOME
                RIGHT 180
                FORWARD 220
                PENDOWN
                
                RIPETI 18 [RIPETI 5 [RIGHT 40 FORWARD 100 RIGHT 120] RIGHT 20]
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
