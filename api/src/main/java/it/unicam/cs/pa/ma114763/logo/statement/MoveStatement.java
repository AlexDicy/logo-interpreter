package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

public class MoveStatement implements Statement {

    private final int distance;
    private final boolean backward;

    public MoveStatement(int distance, boolean backward) {
        this.distance = distance;
        this.backward = backward;
    }
}
