package it.unicam.cs.pa2022.logo;

import it.unicam.cs.pa2022.logo.parser.Statement;

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
}
