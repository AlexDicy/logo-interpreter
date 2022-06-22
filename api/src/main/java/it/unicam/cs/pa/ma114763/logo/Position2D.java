package it.unicam.cs.pa.ma114763.logo;

/**
 * Position2D is a class that represents a position in a 2D space
 * with an x and y coordinate, where x is the horizontal coordinate and y is the vertical coordinate.
 *
 * @author Lorenzo Lapucci
 */
public interface Position2D {
    /**
     * Precision used to compare two positions.
     */
    double EPSILON = 0.00000000001;

    /**
     * @return the x coordinate of the position
     */
    double getX();

    /**
     * @return the y coordinate of the position
     */
    double getY();

    /**
     * Checks whether the given position is equal to this position considering a precision of {@link #EPSILON}.
     * <p>
     * To be used instead of {@link Object#equals(Object)} to avoid floating point errors.
     *
     * @param other the position to compare with this position
     */
    default boolean isSamePosition(Position2D other) {
        return Math.abs(getX() - other.getX()) < EPSILON && Math.abs(getY() - other.getY()) < EPSILON;
    }
}
