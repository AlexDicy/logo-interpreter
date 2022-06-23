package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.drawing.Color;

/**
 * @author Lorenzo Lapucci
 */
public sealed interface SetColorStatement extends Statement permits SetBackgroundColorStatement, SetFillColorStatement, SetStrokeColorStatement {
    Color color();
}
