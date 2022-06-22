package it.unicam.cs.pa.ma114763.logo;

/**
 * @author Lorenzo Lapucci
 */
public class Point implements Position2D {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
}
