package by.gourianova.apptrainer.entity.tetris.ui;

import javax.swing.*;
import java.awt.*;

public class Box extends JPanel {
    private int color;

    public int getColor() {
        return color;
    }

    public Box(int x, int y){
        color=0;
             setSize(Config.HEIGHT,Config.HEIGHT);
        setLocation(x*Config.HEIGHT,y*Config.HEIGHT);
        setLayout(null);
        setBackground(Config.BACK);
    }
    public void setColor(int color){
        this.color=color;
         if (color==0) setBackground(Config.BACK);
          else if(color==2) setBackground(Color.blue);
         else if(color==3) setBackground(Color.green);
         else setBackground(Config.FORE);

    }
}
