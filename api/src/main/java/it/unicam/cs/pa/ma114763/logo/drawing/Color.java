package it.unicam.cs.pa.ma114763.logo.drawing;

/**
 * Represents a color is sRGB color space. Supports transparency.
 *
 * @author Lorenzo Lapucci
 */
public interface Color {
    /**
     * @return the red component of the color, between 0 and 255.
     */
    int getRed();

    /**
     * @return the green component of the color, between 0 and 255.
     */
    int getGreen();

    /**
     * @return the blue component of the color, between 0 and 255.
     */
    int getBlue();

    /**
     * @return the alpha component of the color, between 0 and 255.
     */
    int getAlpha();

    /**
     * @return true if the color is opaque, false if {@link #getAlpha()} is <code>< 255</code>.
     */
    default boolean isOpaque() {
        return getAlpha() == 255;
    }
}
