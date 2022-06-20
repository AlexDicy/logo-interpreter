package it.unicam.cs.pa.ma114763.logo;

/**
 * @author Lorenzo Lapucci
 */
public class RGBColor implements Color {
    private final byte red;
    private final byte green;
    private final byte blue;
    private final byte alpha;

    public RGBColor(byte red, byte green, byte blue, byte alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public RGBColor(byte red, byte green, byte blue) {
        this(red, green, blue, (byte) 255);
    }

    @Override
    public byte getRed() {
        return red;
    }

    @Override
    public byte getGreen() {
        return green;
    }

    @Override
    public byte getBlue() {
        return blue;
    }

    @Override
    public byte getAlpha() {
        return alpha;
    }
}
