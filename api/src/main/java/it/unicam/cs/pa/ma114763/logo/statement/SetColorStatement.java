package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.Statement;

/**
 * @author Lorenzo Lapucci
 */
public sealed interface SetColorStatement extends Statement permits SetBackgroundColorStatement, SetFillColorStatement, SetStrokeColorStatement {
    Color color();
}
