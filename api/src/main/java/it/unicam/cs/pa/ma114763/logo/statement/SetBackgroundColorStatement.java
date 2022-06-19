package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.Statement;

public class SetBackgroundColorStatement implements Statement {
    private final Color color;

    public SetBackgroundColorStatement(Color color) {
        this.color = color;
    }
}
