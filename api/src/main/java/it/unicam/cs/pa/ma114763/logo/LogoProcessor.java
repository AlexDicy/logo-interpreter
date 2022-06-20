package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.statement.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lorenzo Lapucci
 */
public class LogoProcessor implements Processor {
    @Override
    public void execute(List<Statement> statements, Canvas canvas) {
        for (Statement statement : statements) {
            List<Statement> generated = execute(statement, canvas);
            // if the statement generated more statements, execute them
            if (generated != null) {
                execute(generated, canvas);
            }
        }
    }

    @Override
    public @Nullable List<Statement> execute(@NotNull Statement statement, @NotNull Canvas canvas) {
        switch (statement) {
            case SetColorStatement s -> executeColorStatement(s, canvas);
            case PositionStatement s -> executePositionStatement(s, canvas);
            case ClearScreenStatement s -> canvas.clear();
            case RepeatStatement s -> {
                return executeRepeatStatement(s);
            }
            case SetDrawingStatement s -> canvas.setDrawing(s.draw());
            default -> throw new IllegalArgumentException("Unknown statement: " + statement);
        }
        return null;
    }

    private void executeColorStatement(SetColorStatement statement, Canvas canvas) {
        switch (statement) {
            case SetBackgroundColorStatement s -> canvas.setBackgroundColor(s.color());
            case SetFillColorStatement s -> canvas.setFillColor(s.color());
            case SetStrokeColorStatement s -> canvas.setStrokeColor(s.color());
        }
    }

    private void executePositionStatement(PositionStatement statement, Canvas canvas) {
        switch (statement) {
            case HomeStatement s -> canvas.setCurrentPosition(0, 0);
            case MoveStatement s -> executeMoveStatement(s, canvas);
            case RotateAngleStatement s -> canvas.rotate(s.angleRotation());
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

    private List<Statement> executeRepeatStatement(RepeatStatement s) {
        int size = s.times() * s.statements().size();
        List<Statement> statements = new ArrayList<>(size);
        for (int i = 0; i < s.times(); i++) {
            statements.addAll(s.statements());
        }
        return statements;
    }
}
