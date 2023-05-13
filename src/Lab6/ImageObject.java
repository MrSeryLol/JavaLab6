package Lab6;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

public class ImageObject extends Observable implements Runnable {
    Thread thr;
    ExampleImage _image;
    private boolean xplus;
    private boolean yplus;
    int x; int y;
    Color col;
    BufferedImage bi;
    Graphics big; // stands for Buffered Image Graphics
    Toolkit toolkit;
    MediaTracker tracker;
    int width;
    int height;

    public ImageObject(Color col, String text) {
        xplus = true;
        yplus = true;
        x = 0;
        y = 30;
        this.col = col;
        Lab6.Test.count++;
        thr = new Thread(this, Test.count + ":" + text + ":");
        thr.start();
        toolkit = Toolkit.getDefaultToolkit();
        //tracker = new MediaTracker(this);

        //ImageIcon icon = new ImageIcon("../vk_icon.png");
       //Image image = icon.getImage();

        //Image image = toolkit.getImage("../vk_icon.png");
//        //tracker.addImage(image, 0);
//        try {
//            // load all the image for later use
//            tracker.waitForAll();
//        } catch (InterruptedException ex) {
        //}
    }

    public void run(){
        while (true){
            if(x==475) xplus = false;
            if(x==-1) xplus = true;
            if(y==175) yplus = false;
            if(y==29) yplus = true;
            if(xplus) x++; else x--;
            if(yplus) y++; else y--;
            setChanged();notifyObservers (this);
            try{Thread.sleep (10);}
            catch (InterruptedException e){}
        }

        //width = image.getWidth(this);
        //height = image.getHeight(this);

        //bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //big = bi.getGraphics();
        //big.drawImage(image, 0, 0, this);
    }
}
