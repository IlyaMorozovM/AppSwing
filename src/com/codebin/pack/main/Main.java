package com.codebin.pack.main;

import Ierarhy.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi");

//        Line line1 = new Line(0, 0, 50, 70);
//        Rectangle rectangle1 = new Rectangle(20, 20, 100, 100);
//        Square square1 = new Square(300, 300, 100);
//        Triangle triangle1 = new Triangle(50, 50, 100, 100, 50, 70);
//        Ellipse ellipse1 = new Ellipse(400,400, 570, 500);
//        Circle cicrle1 = new Circle(300, 200, 50);

        FigureList figureList = new FigureList();
        figureList.add(new Line(0, 0, 50, 70));
        figureList.add(new Rectangle(20, 20, 150, 100));
        figureList.add(new Square(300, 300, 100));
        figureList.add(new Triangle(300, 370, 350, 420, 400, 370));
        figureList.add(new Ellipse(400,400, 570, 500));
        figureList.add(new Circle(300, 200, 50));

        JFrame frame = new JFrame("App");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MyPanel(figureList));
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
