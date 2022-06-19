package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.Statement;

public class SetStrokeColorStatement implements Statement {
    private final Color color;

    public SetStrokeColorStatement(Color color) {
        this.color = color;
    }
}
