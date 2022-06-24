package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.parser.Parser;
import it.unicam.cs.pa.ma114763.logo.parser.exception.ParserException;
import it.unicam.cs.pa.ma114763.logo.processor.Processor;
import it.unicam.cs.pa.ma114763.logo.statement.Statement;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoInterpreter implements Interpreter {
    private final Processor processor;
    private final DrawingContext drawingContext;

    private boolean initialized = false;
    private List<Statement> statements;

    private LinkedList<Statement> queue;

    public LogoInterpreter(Processor processor, DrawingContext drawingContext) {
        this.processor = processor;
        this.drawingContext = drawingContext;
    }

    @Override
    public void initialize(Parser parser, String program) throws ParserException {
        if (initialized) {
            throw new IllegalStateException("This Interpreter is already initialized");
        }
        initialized = true;
        parseInput(parser, program);
    }

    private void parseInput(Parser parser, String program) throws ParserException {
        statements = parser.parse(program);
        queue = new LinkedList<>(statements);
    }

    @Override
    public void runAll() {
        if (!initialized) {
            throw new IllegalStateException("This Interpreter is not initialized, call initialize() first");
        }
        processor.execute(statements, drawingContext);
    }

    @Override
    public boolean runNext() {
        if (!initialized) {
            throw new IllegalStateException("This Interpreter is not initialized, call initialize() first");
        }
        if (queue.isEmpty()) {
            return false;
        }

        Statement statement = queue.remove();
        List<Statement> result = processor.execute(statement, drawingContext);
        if (result != null) {
            queue.addAll(0, result); // add results to the head of the queue
        }
        return true;
    }
}
