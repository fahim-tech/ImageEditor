package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;

import algorithms.ImageEditorAlgorithms;
import gui.ImageEditor;

public class ButtonActions {

    public static final int NONE = -1;

    protected final ImageEditor jfImageEditor;
    protected final int action;

    public ButtonActions(ImageEditor jFrame) {
        this(jFrame, NONE);
    }

    public ButtonActions(ImageEditor jFrame, int action) {
        this.jfImageEditor = jFrame;
        this.action = action;
    }

    protected boolean isValidTab() {
        return jfImageEditor.getImageTab() != null ? true : false;
    }

    public class Button extends AbstractAction {

        public static final int HORIZONTAL = 0;
        public static final int VERTICAL = 1;
        public static final int NEGATIVE = 2;
        public static final int RRIGHT = 3;
        public static final int RLEFT = 4;
        public static final int GREENS = 5;
        public static final int GRAYS = 6;
        public static final int BLUR = 7;
        public static final int BRIGHT = 8;


        private final int action;

        public Button(int action) {
            this.action = action;
        }

        public void actionPerformed(ActionEvent e) {
            if (!isValidTab()) {
                return;
            }

            BufferedImage temp = jfImageEditor.getImageTab();
            ImageEditorAlgorithms filter = new ImageEditorAlgorithms(temp);

            switch (action) {
                case HORIZONTAL: {
                    temp = filter.flipHorizontal();
                    jfImageEditor.infoLabel.setText("Image flipped horizontally.       ");
                    break;
                }
                case VERTICAL: {
                    temp = filter.flipVertical();
                    jfImageEditor.infoLabel.setText("Image flipped Vertically.       ");
                    break;
                }
                case NEGATIVE: {
                    temp = filter.negative();
                    jfImageEditor.infoLabel.setText("Negative Filter applied.       ");
                    break;
                }
                case RRIGHT: {
                    temp = filter.rRight();
                    jfImageEditor.infoLabel.setText("Image rotated 90 degrees clockwise.       ");
                    break;
                }
                case RLEFT: {
                    temp = filter.rLeft();
                    jfImageEditor.infoLabel.setText("Image rotated 90 degrees counter-clockwise.       ");
                    break;
                }
                case GREENS: {
                    temp = filter.greenS();
                    jfImageEditor.infoLabel.setText("Removed green background.       ");
                    break;
                }
                case GRAYS: {
                    temp = filter.grayscale();
                    jfImageEditor.infoLabel.setText("Grayscale (black-white) filter applied.       ");
                    break;
                }
                case BLUR: {
                    temp = filter.blur();
                    jfImageEditor.infoLabel.setText("Blurred the image.       ");
                    break;

                }
                case BRIGHT: {
                    temp = filter.iBright();
                    jfImageEditor.infoLabel.setText("Image brightness level increased.       ");
                    break;
                }
            }
            jfImageEditor.addImageTab(temp);
        }

    }

}
