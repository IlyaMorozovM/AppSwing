package com.codebin.pack.main;

import Ierarhy.*;

import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class Main {
    public static ClassLoader operatorsLoader;

    private static boolean containsClass(ArrayList<Class> classList, Class clazz) {

        //coercive measure in connection with the use of a dynamic classLoader
        for (Class aClass : classList) {
            if (aClass.getName().equals(clazz.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void load(ArrayList<Class> classList) {
        try {
            getOperators(new File("plugins")).forEach(aClass -> {
                if (!containsClass(classList, aClass)) {
                    classList.add(aClass);
                }
            });
        } catch (MalformedURLException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Class> classList = new ArrayList<Class>();
        classList.add(Circle.class);
        classList.add(Rectangle.class);
        classList.add(Square.class);
        classList.add(Triangle.class);
        classList.add(Ellipse.class);
        classList.add(Line.class);
        classList.add(Polygon.class);
        load(classList);

        frame.add(new MyPanel(classList));
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static ArrayList<Class> getOperators(File operatorFile) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        operatorsLoader = new URLClassLoader(new URL[]{operatorFile.toURI().toURL()});

        File[] files = operatorFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });
        ArrayList<Class> operators = new ArrayList<>();
        for (File file : files) {
            String className = file.getName().substring(0, file.getName().length() - 6);
            Class<?> clazz = operatorsLoader.loadClass(className);
            if (Drawable.class.isInstance(clazz.newInstance())) {
                operators.add(clazz);
            }
        }
        return operators;
    }
}
