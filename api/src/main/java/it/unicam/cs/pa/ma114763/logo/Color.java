package it.unicam.cs.pa.ma114763.logo;

/**
 * Represents a color is sRGB color space. Supports transparency.
 *
 * @author Lorenzo Lapucci
 */
public interface Color {
    /**
     * @return the red component of the color
     */
    byte getRed();

    /**
     * @return the green component of the color
     */
    byte getGreen();

    /**
     * @return the blue component of the color
     */
    byte getBlue();

    /**
     * @return the alpha component of the color
     */
    byte getAlpha();
}
