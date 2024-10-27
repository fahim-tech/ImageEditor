package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

// This controls the view of the image screen under tab.
public class ImagePane extends JScrollPane {


    private BufferedImage image;

    public ImagePane(BufferedImage image) {
        this.image = image;
        setViewportView(new JViewportImage());

    }

    public BufferedImage getImage() {
        return image;
    }

    class JViewportImage extends JViewport {

        public JViewportImage() {
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image, 0, 0, null);
        }
    }

}
