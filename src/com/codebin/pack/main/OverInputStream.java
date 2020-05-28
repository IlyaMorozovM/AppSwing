package com.codebin.pack.main;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class OverInputStream extends ObjectInputStream {
    private ClassLoader _ldr;

    OverInputStream(InputStream str)
            throws IOException {
        super(str);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        {
            try {
                return Main.operatorsLoader.loadClass(desc.getName());
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Plugin " + e.getMessage() + " not found :'(", "alarm", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

    }

}
