package com.rgubaidillin.module5;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Renat_Gubaidullin on 8/13/2016.
 */
public class CustomJavaFileClassLoader extends ClassLoader{



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        byte[] classData = null;

        try {
            classData = loadClassData(name);
            Class<?> c = defineClass(name, classData, 0, classData.length);
            //resolveClass(c);
            //classes.put(name, c);

            return c;
        } catch (IOException e) {
            throw new ClassNotFoundException("Class [" + name
                    + "] could not be found", e);
        }


    }

    /**
     * Load the class file into byte array
     *
     * @param name
     *            The name of the class e.g. com.codeslices.test.TestClass}
     * @return The class file as byte array
     * @throws IOException
     */
    private byte[] loadClassData(String name) throws IOException {
        BufferedInputStream in = new BufferedInputStream(
                ClassLoader.getSystemResourceAsStream(name.replace(".", "/")
                        + ".class"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int i;

        while ((i = in.read()) != -1) {
            out.write(i);
        }

        in.close();
        byte[] classData = out.toByteArray();
        out.close();

        return classData;
    }

    /**
     * Simple usage of the CustomClassLoader implementation
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     */

}
