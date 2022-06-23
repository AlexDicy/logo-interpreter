package it.unicam.cs.pa.ma114763.logo;

import it.unicam.cs.pa.ma114763.logo.drawing.DrawingContext;
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
    public void execute(@NotNull List<Statement> statements, @NotNull DrawingContext drawing) {
        for (Statement statement : statements) {
            List<Statement> generated = execute(statement, drawing);
            // if the statement generated more statements, execute them
            if (generated != null) {
                execute(generated, drawing);
            }
        }
    }

    @Override
    public @Nullable List<Statement> execute(@NotNull Statement statement, @NotNull DrawingContext drawing) {
        switch (statement) {
            case SetColorStatement s -> executeColorStatement(s, drawing);
            case PositionStatement s -> executePositionStatement(s, drawing);
            case ClearScreenStatement s -> drawing.clear();
            case RepeatStatement s -> {
                return executeRepeatStatement(s);
            }
            case SetDrawingStatement s -> drawing.setDrawing(s.draw());
            case SetStrokeSizeStatement s -> drawing.setStrokeSize(s.size());
            default -> throw new IllegalArgumentException("Unknown statement: " + statement);
        }
        return null;
    }

    private void executeColorStatement(SetColorStatement statement, DrawingContext drawing) {
        switch (statement) {
            case SetBackgroundColorStatement s -> drawing.setBackgroundColor(s.color());
            case SetFillColorStatement s -> drawing.setFillColor(s.color());
            case SetStrokeColorStatement s -> drawing.setStrokeColor(s.color());
        }
    }

    private void executePositionStatement(PositionStatement statement, DrawingContext drawing) {
        switch (statement) {
            case HomeStatement s -> {
                double x = drawing.getWidth() / 2.0;
                double y = drawing.getHeight() / 2.0;
                drawing.setCurrentPosition(x, y);
            }
            case MoveStatement s -> executeMoveStatement(s, drawing);
            case RotateAngleStatement s -> drawing.rotate(s.angleRotation());
        }
    }

    private void executeMoveStatement(MoveStatement s, DrawingContext drawing) {
        double angle = drawing.getCurrentDirection();
        double distance = s.distance();
        if (s.backward()) {
            angle += 180;
        }
        // calculate the x and y coordinates of the new position
        double offsetX = Math.cos(Math.toRadians(angle)) * distance;
        double offsetY = -Math.sin(Math.toRadians(angle)) * distance; // negative because y increases upwards
        drawing.move(offsetX, offsetY);
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
