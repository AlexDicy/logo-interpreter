package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.statement.*;

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
            case ClearScreenStatement s -> canvas.clear();
            case HomeStatement s -> canvas.setCurrentPosition(0, 0);
            case MoveStatement s -> executeMoveStatement(s, canvas);
            case RepeatStatement s -> executeRepeatStatement(s, canvas);
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

    private void executeMoveStatement(MoveStatement s, Canvas canvas) {
        double angle = canvas.getCurrentDirection();
        double distance = s.distance();
        if (s.backward()) {
            angle += 180;
        }
        // calculate the x and y coordinates of the new position
        int offsetX = (int) (Math.cos(Math.toRadians(angle)) * distance);
        int offsetY = (int) (Math.sin(Math.toRadians(angle)) * distance);
        canvas.move(offsetX, offsetY);
    }

    private void executeRepeatStatement(RepeatStatement s, Canvas canvas) {
        for (int i = 0; i < s.times(); i++) {
            execute(s.statements(), canvas);
        }
    }
}
