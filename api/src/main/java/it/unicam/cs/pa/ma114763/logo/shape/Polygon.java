package it.unicam.cs.pa.ma114763.logo.shape;

import it.unicam.cs.pa.ma114763.logo.Color;
import it.unicam.cs.pa.ma114763.logo.DrawingContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param fillColor the fill color of the polygon
 * @param strokes   the list of strokes of the polygon, each line must start
 *                  where the previous one ends. The list must contain at least
 *                  3 strokes.
 * @author Lorenzo Lapucci
 */
public record Polygon(Color fillColor, List<Line> strokes) implements Shape {

    public Polygon {
        if (strokes.size() < 3) {
            throw new IllegalArgumentException("A polygon must have at least 3 strokes");
        }
        // validate that a stroke starts where the previous one ends
        Line previousStroke = strokes.get(0);
        for (int i = 1; i <= strokes.size(); i++) {
            Line currentStroke = strokes.get(i % strokes.size());
            if (!currentStroke.start().isSamePosition(previousStroke.end())) {
                throw new IllegalArgumentException("Every stroke must start where the previous one ends");
            }
            previousStroke = currentStroke;
        }
    }

    @Override
    public void draw(DrawingContext ctx) {
        ctx.setFillColor(fillColor);
        for (Line stroke : strokes) {
            stroke.draw(ctx);
        }
        ctx.getDrawingCanvas().fillPolygon(strokes.stream().map(Line::start).collect(Collectors.toList()));
    }
}
