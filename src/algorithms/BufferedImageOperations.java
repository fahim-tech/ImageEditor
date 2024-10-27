package algorithms;

import java.awt.image.BufferedImage;

public abstract class BufferedImageOperations {

    public static final int R = 0, G = 1, B = 2, A = 3;

    protected BufferedImage image, newimage;
    protected final int height, width;

    //Get dimensions from old image
    public BufferedImageOperations(final BufferedImage image) {
        this.image = image;

        height = image.getHeight();
        width = image.getWidth();
    }

    // RGBA components to RGBA number
    public int colorRGBA(final int r, final int g, final int b, final int a) {
        return (a << 24 | r << 16 | g << 8 | b);
    }

    // RGBA number to RGBA components
    public int[] getRGBAComponents(final int color) {
        return new int[] { (color >> 16) & 0xFF, (color >> 8) & 0xFF, (color & 0xFF), (color >> 24) };
    }

}
