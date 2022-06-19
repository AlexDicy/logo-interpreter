package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

import java.util.List;

public class RepeatStatement implements Statement {
    private final int times;
    private final List<Statement> statements;

    public RepeatStatement(int times, List<Statement> statements) {
        this.times = times;
        this.statements = statements;
    }
}
