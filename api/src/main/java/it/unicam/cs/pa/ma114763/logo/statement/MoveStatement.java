package it.unicam.cs.pa.ma114763.logo.statement;

import it.unicam.cs.pa.ma114763.logo.Statement;

/**
 * @author Lorenzo Lapucci
 */
public record MoveStatement(int distance, boolean backward) implements Statement {
}
