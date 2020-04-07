package com.codebin.pack.main;

import Ierarhy.Drawable;
import Ierarhy.FigureList;
import Ierarhy.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    FigureList figureList = new FigureList();
    Class clazz = Line.class;
    int figureCount = getZeroed(clazz).getDots();
    int count = figureCount;

    Color color = Color.black;
    int r = 255;
    int g = 255;
    int b = 255;
    ArrayList<Point> points = new ArrayList<>();

    private static Drawable getZeroed(Class clazz){
        Drawable drawable = null;
        int count = clazz.getConstructors()[0].getParameterCount();
        Object[] args = new Object[count];
        for (int i = 0; i < count; i++) {
            if (clazz.getConstructors()[0].getParameterTypes()[i] == int.class) {
                args[i] = new Integer(0);
            }
        }
        try {
            drawable = (Drawable) clazz.getConstructors()[0].newInstance(args);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return drawable;
    }

    public MyPanel(ArrayList<Class> classList) {
    super();
    this.addMouseWheelListener(new MouseWheelListener() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
//                super.mouseWheelMoved(e);
                color = new Color((int)(Math.random() * 0x1000000));
        }
    });
    this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            //MouseButtonLLeft
            if (e.getButton() == 1){
                points.add(new Point(e.getX(), e.getY()));
                count--;

            }
            //MouseButtonLRight
            else if (e.getButton() == 3){
                clazz = chooseObject(classList);
                figureCount = getZeroed(clazz).getDots();
                count = figureCount;
                points = new ArrayList<>();
            }
            else if (e.getButton() == 2){
                figureList = new FigureList();
                repaint();
            }

            if (count == 0){
                Drawable temp = getZeroed(clazz);
                Point[] points2 = new Point[figureCount];
                for (int i = 0; i < figureCount; i++){
                    points2[i] = points.get(i);
                }
                temp = temp.resetByDots(points2);
                temp.setColor(color);
                figureList.add(temp);
                count = figureCount;
                points = new ArrayList<>();
                repaint();
            }
        }


    });
    }

    private Class chooseObject(ArrayList<Class> classList){
        JComboBox jcb = new JComboBox(classList.toArray());
        JOptionPane.showMessageDialog( null, jcb, "Select your figure", JOptionPane.QUESTION_MESSAGE);
        return (Class) jcb.getSelectedItem();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        figureList.drawList(g);

    }
}
