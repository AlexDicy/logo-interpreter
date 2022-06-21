package it.unicam.cs.pa.ma114763.logo.ui;

import it.unicam.cs.pa.ma114763.logo.Color;
import javafx.scene.paint.Paint;

import static javafx.scene.paint.Color.rgb;

/**
 * @author Lorenzo Lapucci
 */
public class Utils {

    /**
     * Converts the color to a JavaFX paint.
     *
     * @return the JavaFX paint
     */
    public static Paint toFXPaint(Color color) {
        return rgb(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() / 255.0);
    }
}
