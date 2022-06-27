package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
import it.unicam.cs.pa.ma114763.logo.parser.Parser;
import it.unicam.cs.pa.ma114763.logo.parser.SingleParseResult;
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
    private List<SingleParseResult> parseResults;

    private LinkedList<Statement> queue;

    private int parseResultIndex = 0;

    private int lastLine = 0;

    public LogoInterpreter(Processor processor, DrawingContext drawingContext) {
        this.processor = processor;
        this.drawingContext = drawingContext;
    }

    @Override
    public List<SingleParseResult> initialize(Parser parser, String program) throws ParserException {
        if (initialized) {
            throw new IllegalStateException("This Interpreter is already initialized");
        }
        initialized = true;
        return parseInput(parser, program);
    }

    private List<SingleParseResult> parseInput(Parser parser, String program) throws ParserException {
        parseResults = parser.parseWithResults(program);
        reset();
        return parseResults;
    }

    @Override
    public void runAll() {
        if (!initialized) {
            throw new IllegalStateException("This Interpreter is not initialized, call initialize() first");
        }
        processor.execute(getStatements(), drawingContext);
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

    @Override
    public boolean runNextRoot() {
        if (!initialized) {
            throw new IllegalStateException("This Interpreter is not initialized, call initialize() first");
        }
        if (parseResultIndex < parseResults.size()) {
            SingleParseResult parseResult = parseResults.get(parseResultIndex);
            List<Statement> results = processor.execute(parseResult.statement(), drawingContext);
            if (results != null) {
                processor.execute(results, drawingContext); // execute the remaining results
            }
            lastLine = parseResult.index();
            parseResultIndex++;
            return true;
        }
        return false;
    }

    @Override
    public int getLastRanRootIndex() {
        return lastLine;
    }

    @Override
    public void reset() {
        queue = new LinkedList<>(getStatements());
        parseResultIndex = 0;
        lastLine = 0;
    }

    private List<Statement> getStatements() {
        return parseResults.stream().map(SingleParseResult::statement).toList();
    }
}
