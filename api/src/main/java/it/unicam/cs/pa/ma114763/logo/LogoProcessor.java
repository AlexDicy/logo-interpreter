package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.statement.SetBackgroundColorStatement;
import it.unicam.cs.pa.ma114763.logo.statement.SetColorStatement;
import it.unicam.cs.pa.ma114763.logo.statement.SetFillColorStatement;
import it.unicam.cs.pa.ma114763.logo.statement.SetStrokeColorStatement;

import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoProcessor implements Processor {
    @Override
    public void execute(List<Statement> statements, Canvas canvas) {
        for (Statement statement : statements) {
            execute(statement, canvas);
        }
    }

    @Override
    public void execute(Statement statement, Canvas canvas) {
        switch (statement) {
            case SetColorStatement s -> executeColorStatement(s, canvas);
            default -> throw new IllegalArgumentException("Unknown statement: " + statement);
        }
    }

    private void executeColorStatement(SetColorStatement statement, Canvas canvas) {
        switch (statement) {
            case SetBackgroundColorStatement s -> canvas.setBackgroundColor(s.color());
            case SetFillColorStatement s -> canvas.setFillColor(s.color());
            case SetStrokeColorStatement s -> canvas.setStrokeColor(s.color());
        }
    }
}
