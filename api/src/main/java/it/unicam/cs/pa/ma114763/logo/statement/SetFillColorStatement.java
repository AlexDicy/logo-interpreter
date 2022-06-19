package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.Statement;

public class SetFillColorStatement implements Statement {
    private final Color color;

    public SetFillColorStatement(Color color) {
        this.color = color;
    }
}
