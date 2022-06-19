package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

public class SetDrawingStatement implements Statement {
    private final boolean draw;

    public SetDrawingStatement(boolean draw) {
        this.draw = draw;
    }
}
