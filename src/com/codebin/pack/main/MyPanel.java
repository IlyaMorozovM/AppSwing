package com.codebin.pack.main;

import Ierarhy.*;
import Ierarhy.Rectangle;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    FigureList figureList;

    public MyPanel(FigureList figureList) {
    super();
    this.figureList = figureList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        figureList.drawList(g);
       // g.setColor(Color.blue);
       // g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }
}
