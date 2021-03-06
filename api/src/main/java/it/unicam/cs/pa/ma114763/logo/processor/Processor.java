package it.unicam.cs.pa.ma114763.logo.processor;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.statement.Statement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Given a list of statements, this class executes them on a drawing.
 *
 * @author Lorenzo Lapucci
 */
public interface Processor {
    /**
     * Executes the given list of statements on the given canvas.
     *
     * @param statements the list of statements to execute
     * @param drawing    the canvas on which to execute the statements
     */
    void execute(@NotNull List<Statement> statements, @NotNull DrawingContext drawing);

    /**
     * Executes exactly one statement on the given canvas.
     * Returns null if the statement's execution didn't generate more statements.
     * Otherwise, returns the list of statements generated by the execution.
     *
     * @param statement the statement to execute
     * @param drawing   the canvas on which to execute the statement
     * @return the list of statements generated by the execution, or null
     * if the statement's execution didn't generate more statements
     */
    @Nullable List<Statement> execute(@NotNull Statement statement, @NotNull DrawingContext drawing);
}
