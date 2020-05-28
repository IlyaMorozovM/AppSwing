package com.codebin.pack.main;

import Ierarhy.Drawable;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends ClassLoader{
    File directory;
    public MyClassLoader(File directory){
    this.directory=directory;
    }

    public Class loadClass (String name) throws ClassNotFoundException{
        return loadClass(name,true);
    }

    public Class loadClass (String classname, boolean resolve) throws ClassNotFoundException {
        try {
            Class c = findLoadedClass(classname);
            if (c == null) {
                try { c = findSystemClass(classname); }
                catch (Exception ex) {}
            }
            if (c == null) {
                String filename = (classname).replace('.',File.separatorChar)+".class";
                //classname="Ierarhy/"+classname;
                File f = new File(directory, filename);
                int length = (int) f.length();
                byte[] classbytes = new byte[length];
                DataInputStream in = new DataInputStream(new FileInputStream(f));
                in.readFully(classbytes);
                in.close();
                //System.out.println(classname);
                c = defineClass(classname, classbytes, 0, length);
            }
            if (resolve) resolveClass(c);
            return c;
        }
        catch (Exception ex) { throw new ClassNotFoundException(ex.toString()); }
    }

    static List<Drawable> loadAllJarClasses(String jarpath) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        URL[] urls = { new URL("jar:file:" + jarpath+"!/") };
        URLClassLoader cl = URLClassLoader.newInstance(urls);
        List<Drawable>plugins=new ArrayList<>();
        JarFile jarFile = new JarFile(jarpath);
        Enumeration<JarEntry> e = jarFile.entries();
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class c = cl.loadClass(className);
            Class[] intf = c.getInterfaces();
            for (int j=0; j<intf.length; j++) {
                if (intf[j].getName().equals("PlugTest")) {
                    // the following line assumes that PluginFunction has a no-argument constructor
                    Drawable pf = (Drawable) c.newInstance();
                    plugins.add(pf);
                    System.out.println("loaded "+pf.getClass()+" from "+jarFile.getName());
                    continue;
                }
            }
        }
        return plugins;
    }

    static List<Drawable> getPlugins(String pluginsDir) {
        List<Drawable>plugins=new ArrayList<>();
        File dir = new File(System.getProperty("user.dir") + File.separator + pluginsDir);
        ClassLoader cl = new MyClassLoader(dir);
        if (dir.exists() && dir.isDirectory()) {
            // we'll only load classes directly in this directory -
            // no subdirectories, and no classes in packages are recognized
            String[] files = dir.list();
            for (int i=0; i<files.length; i++) {
                try {
                    // only consider files ending in ".class"
                    if (files[i].endsWith(".jar")){
                        plugins.addAll(loadAllJarClasses(dir+File.separator+files[i]));
                    }
                    if (! files[i].endsWith(".class"))
                        continue;
                    Class c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
                  //  Class[] intf = c.getInterfaces();
                   // for (int j=0; j<intf.length; j++) {
                      //  if (intf[j].getName().equals("Ierarhy.Drawable")) {
                            // the following line assumes that PluginFunction has a no-argument constructor

                    Method[] m = c.getMethods();
                    for(int j=0;j<m.length;j++){
                        if(m[j].getReturnType().equals(Drawable.class)){
                            Drawable pf = ((Drawable) c.newInstance());
                            plugins.add(pf);
                            System.out.println("loaded "+pf.getClass()+" from "+dir);
                            continue;
                        }
                    }

//                            Drawable pf = (Drawable) c.newInstance();
//                            plugins.add(pf);
//                            System.out.println("loaded "+pf.getClass()+" from "+dir);
//                            continue;
                       // }
                //    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.err.println("File " + files[i] + " does not contain a valid PluginFunction class.");
                }
            }
        }
        return plugins;
    }
}
