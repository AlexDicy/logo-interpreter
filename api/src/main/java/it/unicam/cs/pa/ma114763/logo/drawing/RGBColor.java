package it.unicam.cs.pa.ma114763.logo.drawing;

/**
 * @author Lorenzo Lapucci
 */
public class RGBColor implements Color {
    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    public RGBColor(int red, int green, int blue, int alpha) {
        this.red = validateByte(red);
        this.green = validateByte(green);
        this.blue = validateByte(blue);
        this.alpha = validateByte(alpha);
    }

    public RGBColor(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public int getAlpha() {
        return alpha;
    }

    private static int validateByte(int value) throws IllegalArgumentException {
        if (value < 0 || value > 255) {
            throw new IllegalArgumentException("Invalid byte value: " + value + " (must be between 0 and 255)");
        }
        return value;
    }
}
