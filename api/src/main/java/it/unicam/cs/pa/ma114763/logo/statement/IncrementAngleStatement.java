package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

public class IncrementAngleStatement implements Statement {
    private final int angleIncrement;

    public IncrementAngleStatement(int angleIncrement) {
        this.angleIncrement = angleIncrement;
    }
}
