package Big_Example;

import java.awt.*;
import java.util.Observable;

public class Ball extends Observable implements Runnable{
    Thread thr;
    private boolean xplus;
    private boolean yplus;
    int x; int y;
    Color col;
    public Ball (Color col, String text) {
        xplus = true; yplus = true;
        x = 0; y = 30;
        this.col = col;
        Test.count++;
        thr = new Thread(this,Test.count+":"+text+":");
        thr.start();
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
    }
}
