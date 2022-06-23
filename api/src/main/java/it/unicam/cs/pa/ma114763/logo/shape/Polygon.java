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

    /**
     * Serializes the polygon into a command with the following format:
     * <p>
     * <code>POLYGON &lt;n&gt; &lt;fr&gt; &lt;fg&gt; &lt;fb&gt; [f-alpha]<br>
     * &lt;x<sub>0</sub>&gt; &lt;y<sub>0</sub>&gt; &lt;r<sub>0</sub>&gt; &lt;g<sub>0</sub>&gt; &lt;b<sub>0</sub>&gt; [alpha<sub>0</sub>] &lt;size<sub>0</sub>&gt;<br>
     * &lt;x<sub>1</sub>&gt; &lt;y<sub>1</sub>&gt; &lt;r<sub>1</sub>&gt; &lt;g<sub>1</sub>&gt; &lt;b<sub>1</sub>&gt; [alpha<sub>1</sub>] &lt;size<sub>1</sub>&gt;<br>
     * ...<br>
     * &lt;x<sub>n-1</sub>&gt; &lt;y<sub>n-1</sub>&gt; &lt;r<sub>n-1</sub>&gt; &lt;g<sub>n-1</sub>&gt; &lt;b<sub>n-1</sub>&gt; [alpha<sub>n-1</sub>] &lt;size<sub>n-1</sub>&gt;<br>
     *
     * @return the serialized polygon
     */
    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder();
        sb.append("POLYGON ").append(strokes.size()).append(" ");
        appendColor(sb, fillColor);

        for (Line stroke : strokes) {
            sb.append("\n").append(stroke.start().getX()).append(" ").append(stroke.start().getY()).append(" ");
            appendColor(sb, stroke.color()).append(" ").append(stroke.size());
        }
        return sb.toString();
    }

    private static StringBuilder appendColor(StringBuilder sb, Color color) {
        sb.append(color.getRed()).append(" ").append(color.getGreen()).append(" ").append(color.getBlue());
        if (!color.isOpaque()) {
            sb.append(" ").append(color.getAlpha());
        }
        return sb;
    }
}
