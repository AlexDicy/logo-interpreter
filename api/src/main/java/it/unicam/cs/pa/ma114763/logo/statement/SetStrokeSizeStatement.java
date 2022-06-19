package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

public class SetStrokeSizeStatement implements Statement {
    private final int size;

    public SetStrokeSizeStatement(int size) {
        this.size = size;
    }
}
