package Lab6;

import Big_Example.WindowAdapter2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Application extends Frame implements Observer, ActionListener, ItemListener {
    private LinkedList LL = new LinkedList();
    private Color col;
    private Frame f;
    private Button b;

    private Button updateNumBtn;

    private Button updateSpeedBtn;
    private Choice colorChoice;

    private Label activeObjectLabel;
    private Label numLabel;
    private Label newNumLabel;

    private Label newSpeedLabel;

    private Choice activeObject;
    private Choice speedChoice;

    private Choice newSpeedChoice;
    private Choice textChoice;
    private TextField numField;

    private TextField newNumField;
    Application(){
        this.addWindowListener(new Big_Example.WindowAdapter2());
        f = new Frame();
        f.setSize(new Dimension(300,100));
        f.setTitle("Контроль");
        f.setLayout(new GridLayout(2, 5));
        f.addWindowListener(new WindowAdapter2());
        b = new Button("Пуск");
        b.setSize(new Dimension(10,40));
        b.setActionCommand("OK");
        b.addActionListener(this);
        f.add(b, new Point(20,20));

        colorChoice = new Choice();
        colorChoice.addItem("Синий");
        colorChoice.addItem("Зелёный");
        colorChoice.addItem("Красный");
        colorChoice.addItem("Чёрный");
        colorChoice.addItem("Жёлтый");
        colorChoice.addItemListener(this);
        f.add(colorChoice, new Point(60,20));

        textChoice = new Choice();
        textChoice.addItem("Привет");
        textChoice.addItem("Пока");
        textChoice.addItem("Как дела?");
        textChoice.addItem("Нормально");
        textChoice.addItem("А сам как?");
        f.add(textChoice);

        speedChoice = new Choice();
        speedChoice.addItem("1");
        speedChoice.addItem("4");
        speedChoice.addItem("9");
        speedChoice.addItem("16");
        speedChoice.addItem("25");
        speedChoice.addItem("36");
        f.add(speedChoice);

        numLabel = new Label("Напишите номер объекта");
        f.add(numLabel);

        numField = new TextField();
        f.add(numField);

        activeObjectLabel = new Label("Выберите активный объект:");
        f.add(activeObjectLabel);

        activeObject = new Choice();
        f.add(activeObject);

        newNumLabel = new Label("Выберите новый номер");
        f.add(newNumLabel);
        newNumField = new TextField();
        f.add(newNumField);
        updateNumBtn = new Button("Обновить номер");
        updateNumBtn.setActionCommand("UpdateNum");
        updateNumBtn.addActionListener(this);
        f.add(updateNumBtn);

        newSpeedLabel = new Label("Выберите новую скорость");
        f.add(newSpeedLabel);
        newSpeedChoice = new Choice();
        newSpeedChoice.addItem("1");
        newSpeedChoice.addItem("4");
        newSpeedChoice.addItem("9");
        newSpeedChoice.addItem("16");
        newSpeedChoice.addItem("25");
        f.add(newSpeedChoice);
        updateNumBtn = new Button("Обновить скорость");
        updateNumBtn.setActionCommand("UpdateSpeed");
        updateNumBtn.addActionListener(this);
        f.add(updateNumBtn);




        f.setVisible(true);
        this.setSize(500,300);
        this.setVisible(true);
        this.setLocation(100, 300);
    }
    public void update(Observable o, Object arg) {
        TextObject text = (TextObject) arg;
        System.out.println ("x= " + text.thr.getName() + text.x + " " + text.y);
        repaint();
    }
    public void paint (Graphics g) {
        if (!LL.isEmpty()){
            for (Object LL1 : LL) {
                TextObject text = (TextObject) LL1;
                g.setColor(text.col);
                g.drawString(text._text + " " + text._numObj, text.x, text.y);
            }
        }
    }
    public void itemStateChanged (ItemEvent iE) {}
    public void actionPerformed (ActionEvent aE) {
        String str = aE.getActionCommand();
        if (str.equals ("OK")){
            switch (colorChoice.getSelectedIndex()) {
                case 0: col= Color.blue; break;
                case 1: col= Color.green; break;
                case 2: col= Color.red; break;
                case 3: col= Color.black; break;
                case 4: col= Color.yellow; break;
            }

            for (Object text : LL) {
                TextObject newText = (TextObject)text;
                if(newText._numObj == Integer.parseInt(numField.getText())) {
                    System.out.println("Такой номер фигуры уже существует");
                    return;
                }
            }
            TextObject text = new TextObject(col, this.textChoice.getSelectedItem(),
                    Integer.parseInt(this.speedChoice.getSelectedItem()), this.numField.getText(), this);
            LL.add(text);
            activeObject.addItem(text._text + text._numObj);
            text.addObserver(this);
        } else if (str.equals("UpdateNum")) {
            int newNum;
            try {
                newNum = Integer.parseInt(newNumField.getText());
            } catch(Exception e) {
                System.out.println(e);
                return;
            }
            for (Object text: LL) {
                TextObject newText = (TextObject)text;
                if(newText._numObj == newNum) {
                    System.out.println("Такой номер уже существует");
                    return;
                }
            }
            for (Object text: LL) {
                TextObject txt = (TextObject)text;
                if((txt._text + txt._numObj).equals(activeObject.getSelectedItem())) {
                    activeObject.remove(txt._text + txt._numObj);
                    txt._numObj = newNum;
                    activeObject.addItem(txt._text + txt._numObj);
                }
            }
        } else if (str.equals("UpdateSpeed")) {

            for (Object text : LL) {
                TextObject txt = (TextObject) text;
                if ((txt._text + txt._numObj).equals(activeObject.getSelectedItem())) {
                    txt._speed = Integer.parseInt(newSpeedChoice.getSelectedItem());
                }
            }
        }
        repaint();
    }
}
