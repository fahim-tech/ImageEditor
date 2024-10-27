package algorithms;

import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ImageEditorAlgorithms extends BufferedImageOperations {

    public static final int TYPE_INT_ARGB = 2;

    public ImageEditorAlgorithms(BufferedImage image) {
        super(image);
    }

    public BufferedImage negative() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);
                int[] rgb = getRGBAComponents(px);
                newimage.setRGB(i, j, colorRGBA(255-rgb[R], 255-rgb[G],255-rgb[B], rgb[A]));
            }
        }

        return newimage;
    }

    public BufferedImage greenS() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);
                int[] rgb = getRGBAComponents(px);

                if(rgb[A] != 0 && rgb[G] >= rgb[R] + rgb[B]) {
                    newimage.setRGB(i, j, 0);
                } else {
                    newimage.setRGB(i, j, px);
                }

            }
        }

        return newimage;
    }

    public BufferedImage grayscale() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);

                int[] rgb = getRGBAComponents(px);
                int c = (rgb[R] + rgb[G] + rgb[B]) / 3;

                newimage.setRGB(i, j, colorRGBA(c, c, c, rgb[A]));
            }
        }

        return newimage;
    }

    public BufferedImage flipHorizontal() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);

                int y = height - j - 1;
                newimage.setRGB(i, y, px);
            }
        }

        return newimage;
    }

    public BufferedImage flipVertical() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);

                int x = width - i - 1;
                newimage.setRGB(x, j, px);
            }
        }

        return newimage;
    }

    public BufferedImage rRight() {
        newimage = new BufferedImage(height, width, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);

                int y = height - j - 1;
                newimage.setRGB(y, i, px);
            }
        }

        return newimage;
    }

    public BufferedImage rLeft() {
        newimage = new BufferedImage(height, width, TYPE_INT_ARGB);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int px = image.getRGB(i, j);

                int x = width - i - 1;
                newimage.setRGB(j, x, px);
            }
        }

        return newimage;
    }
    public BufferedImage blur() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        int blurboxlength = 1;
        int arraylength = 4 * blurboxlength * blurboxlength;

        int colorarray[] = new int[arraylength];

        for (int j = blurboxlength; j < height - blurboxlength; j++) {
            for (int i = blurboxlength; i < width - blurboxlength; i++) {

                int count = 0;
                for (int y  = j - blurboxlength; y < j + blurboxlength; y++) {
                    for (int x = i - blurboxlength; x < i + blurboxlength; x++) {
                        colorarray[count++] = image.getRGB(x, y);
                    }
                }

                int a = 0, r = 0, g = 0, b = 0;
                int[] rgb;

                for(int k = 0; k < arraylength; k++) {
                    rgb = getRGBAComponents(colorarray[k]);
                    a += rgb[A];
                    r += rgb[R];
                    g += rgb[G];
                    b += rgb[B];
                }

                a = a / arraylength;
                r = r / arraylength;
                g = g / arraylength;
                b = b / arraylength;

                newimage.setRGB(i, j, colorRGBA(r, g, b, a));

            }
        }

        return newimage;
    }

    public BufferedImage iBright() {
        newimage = new BufferedImage(width, height, TYPE_INT_ARGB);

        float brightenFactor = 1.2f;

        RescaleOp rescale = new RescaleOp(brightenFactor, 0, null);
        image = rescale.filter(image, newimage);

        return newimage;
    }

}
