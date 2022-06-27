package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.parser.Parser;
import it.unicam.cs.pa.ma114763.logo.parser.SingleParseResult;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;

import java.util.List;

/**
 * This class represents an interpreter for a language, its main implementation
 * is the {@link LogoInterpreter} which represents a Logo language interpreter.
 *
 * @author Lorenzo Lapucci
 */
public interface Interpreter {
    /**
     * Initializes the interpreter with the given parser and program.
     * Must be called before calling {@link #runAll()} or {@link #runNext()}.
     *
     * @param parser  the parser to use
     * @param program the program to interpret
     * @return the list of parsing results
     * @throws ParserException       if the program contains syntax errors
     * @throws IllegalStateException if the interpreter is already initialized
     */
    List<SingleParseResult> initialize(Parser parser, String program) throws ParserException;

    /**
     * Runs the program from the beginning until the end.
     * It runs every statement of the program, regardless of whether it has been
     * executed already or not.
     * <p>
     * Must be called only after calling {@link #initialize(Parser, String)}.
     *
     * @throws IllegalStateException if the interpreter is not initialized
     * @see #initialize
     */
    void runAll();

    /**
     * Runs the next statement of the program.
     * <p>
     * Must be called only after calling {@link #initialize(Parser, String)}.
     *
     * @return true if a statement has been executed, false if the program is finished
     * @throws IllegalStateException if the interpreter is not initialized
     * @see #initialize
     */
    boolean runNext();

    /**
     * Clears the queue of statements to be executed and fills it again
     * with the original statements of the program.
     */
    void resetQueue();
}
