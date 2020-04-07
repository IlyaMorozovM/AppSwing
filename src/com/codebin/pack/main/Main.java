package com.codebin.pack.main;

import Ierarhy.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("App");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<Class> classList = new ArrayList<Class>();
        classList.add(Circle.class);
        classList.add(Rectangle.class);
        classList.add(Square.class);
        classList.add(Triangle.class);
        classList.add(Ellipse.class);
        classList.add(Line.class);
        frame.add(new MyPanel(classList));
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
