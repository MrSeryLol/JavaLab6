package Big_Example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Balls extends Frame implements Observer, ActionListener, ItemListener {
    private LinkedList LL = new LinkedList();
    private Color col;
    private Frame f;
    private Button b;
    private Choice c;
    private TextField tf;
    Balls(){
        this.addWindowListener(new WindowAdapter2());
        f = new Frame();
        f.setSize(new Dimension(300,100));
        f.setTitle("Контроль");
        f.setLayout(new GridLayout());
        f.addWindowListener(new WindowAdapter2());
        b = new Button("Пуск");
        b.setSize(new Dimension(10,40));
        b.setActionCommand("OK");
        b.addActionListener(this);
        f.add(b, new Point(20,20));
        c = new Choice();
        c.addItem("Синий");
        c.addItem("Зелёный");
        c.addItem("Красный");
        c.addItem("Чёрный");c.addItem("Жёлтый");
        c.addItemListener(this);
        f.add(c, new Point(60,20));
        tf = new TextField();
        f.add(tf);
        f.setVisible(true);
        this.setSize(500,200);
        this.setVisible(true);
        this.setLocation(100, 150);
    }
    public void update(Observable o, Object arg) {
        Ball ball = (Ball)arg;
        System.out.println ("x= " + ball.thr.getName() + ball.x);
        repaint();
    }
    public void paint (Graphics g) {
        if (!LL.isEmpty()){
            for (Object LL1 : LL) {
                Ball ball = (Ball) LL1;
                g.setColor(ball.col);
                g.drawOval(ball.x, ball.y, 20, 20);
            }
        }
    }
    public void itemStateChanged (ItemEvent iE) {}
    public void actionPerformed (ActionEvent aE) {
        String str = aE.getActionCommand();
        if (str.equals ("OK")){
            switch (c.getSelectedIndex()) {
                case 0: col= Color.blue; break;
                case 1: col= Color.green; break;
                case 2: col= Color.red; break;
                case 3: col= Color.black; break;
                case 4: col= Color.yellow; break;
            }
            Ball ball= new Ball(col, this.tf.getText());
            LL.add(ball);
            ball.addObserver(this);
        }
        repaint();
    }
}
