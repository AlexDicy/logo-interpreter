package it.unicam.cs.pa.ma114763.logo;

/**
 * @author Lorenzo Lapucci
 */
public class Point implements Position2D {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
