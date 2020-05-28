package com.codebin.pack.main;

import Ierarhy.Drawable;
import Ierarhy.FigureList;
import Ierarhy.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class MyPanel extends JPanel {
    private static boolean isDynamic = false;
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
        Constructor<?>[] constructors= clazz.getConstructors();
        Arrays.sort(constructors,new ConstructorsComparator());
        int count = constructors[0].getParameterCount();
        Object[] args = new Object[count];
        for (int i = 0; i < count; i++) {

            if (constructors[0].getParameterTypes()[i] == int.class) {
                args[i] = 0;
            }
        }
        try {
            drawable = (Drawable) constructors[0].newInstance(args);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return drawable;
    }

    private static class ConstructorsComparator implements Comparator<Constructor<?>>
    {
        @Override
        public int compare(Constructor<?> o1, Constructor<?> o2) {
            int c1;
            c1=o1.getParameterCount();
            int c2;
            c2=o2.getParameterCount();
            return Integer.compare(c1,c2);
        }
    }

    public MyPanel(ArrayList<Class> classList) {
    super();

    this.addMouseWheelListener(e -> {
//                super.mouseWheelMoved(e);
            color = new Color((int)(Math.random() * 0x1000000));
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
                if (isDynamic) {
                    figureCount = dynamicPointsCount;
                }
                else
                {
                    figureCount = getZeroed(clazz).getDots();
                }
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

        JButton button1 = new JButton("save");
        JButton button2 = new JButton("load");

        button1.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    serialize(figureList.getList(), chooser.getSelectedFile().getAbsolutePath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "alarm", JOptionPane.ERROR_MESSAGE); }
                System.out.println(chooser.getSelectedFile().getAbsolutePath());
            }
        });
        button2.addActionListener(e -> {
            Main.load(classList);
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {

                    figureList.setList((ArrayList<Drawable>) deSerialize(chooser.getSelectedFile().getAbsolutePath()));
                    repaint();
                } catch (IOException | ClassNotFoundException ex) {
                // ex.printStackTrace();
                    if (!(ex instanceof ClassNotFoundException)) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "alarm", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        this.add(button1);
        this.add(button2);


    }



    public Object deSerialize(String pathname) throws IOException, ClassNotFoundException {
        Object retObject = null;

        FileInputStream fileIn = new FileInputStream(pathname);
//        ObjectInputStream objIn = new ObjectInputStream(fileIn);
        OverInputStream objIn = new OverInputStream(fileIn);
        retObject= objIn.readObject();

        objIn.close();
        fileIn.close();



        return retObject;
    }

    private void serialize(Object object, String pathname) throws IOException {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        ObjectOutputStream out = null;
//
//
//        ObjectInput in = null;
//        try {
//            out = new ObjectOutputStream(bos);
//            out.writeObject(object);
//            out.flush();
//            byte[] yourBytes = bos.toByteArray();
//
//            ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
//            in = new ObjectInputStream(bis);
//            Object o = null;
//            try {
//                o = in.readObject();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println(o.equals(object));
//        } finally {
//            try {
//                bos.close();
//            } catch (IOException ex) {
//                // ignore close exception
//            }
//        }


        FileOutputStream fileOut = new FileOutputStream(pathname);
       // OutputStream buffer = new BufferedOutputStream(fileOut);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(object);
        objOut.close();
       // buffer.close();
        fileOut.close();


    }

    private static int dynamicPointsCount = 0;


    private Class chooseObject(ArrayList<Class> classList){

        Main.load(classList);


        JComboBox jcb = new JComboBox(classList.toArray());
        JOptionPane.showMessageDialog( null, jcb, "Select your figure", JOptionPane.QUESTION_MESSAGE);

        int temp = getZeroed((Class) jcb.getSelectedItem()).getDots();
        if (temp < 0) {
            dynamicPointsCount = -1;
            isDynamic = true;
            JFrame frame = new JFrame();
            do {


                Object result = JOptionPane.showInputDialog(frame, "Enter number of sides:");
                try {
                    dynamicPointsCount = Integer.parseInt((String) result);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            while (dynamicPointsCount < -temp);
        }
        else {
            isDynamic = false;
        }
        return (Class) jcb.getSelectedItem();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        figureList.drawList(g);

    }

}
