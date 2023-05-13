package Lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ExampleImage extends JFrame {
    BufferedImage bi;
    Graphics big; // stands for Buffered Image Graphics
    Toolkit toolkit;
    MediaTracker tracker;
    int width;
    int height;

    public ExampleImage() {
        toolkit = Toolkit.getDefaultToolkit();
        tracker = new MediaTracker(this);
        Image image = toolkit.getImage("../vk_icon.png");
        tracker.addImage(image, 0);
        try {
            tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
    }
}
