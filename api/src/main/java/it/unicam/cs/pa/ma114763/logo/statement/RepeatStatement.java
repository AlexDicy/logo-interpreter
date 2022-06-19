package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public record RepeatStatement(int times, List<Statement> statements) implements Statement {
}
