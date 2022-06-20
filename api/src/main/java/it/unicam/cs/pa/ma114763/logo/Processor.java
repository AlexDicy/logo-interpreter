package it.unicam.cs.pa.ma114763.logo;

import java.util.List;

/**
 * Given a list of statements, this class executes them on a canvas.
 *
 * @author Lorenzo Lapucci
 */
public interface Processor {
    /**
     * Executes the given list of statements on the given canvas.
     *
     * @param statements the list of statements to execute
     * @param canvas     the canvas on which to execute the statements
     */
    void execute(List<Statement> statements, Canvas canvas);

    /**
     * Executes the statement on the given canvas.
     *
     * @param statement the statement to execute
     * @param canvas    the canvas on which to execute the statement
     */
    void execute(Statement statement, Canvas canvas);
}
