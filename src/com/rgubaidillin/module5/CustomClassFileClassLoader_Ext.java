package com.rgubaidillin.module5;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Renat_Gubaidullin on 8/13/2016.
 */
public class CustomClassFileClassLoader_Ext extends CustomClassFileClassLoader {


     public void startMultipleLoad() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
         while (true) {
             CustomClassFileClassLoader_Ext customClassLoader_ext = new CustomClassFileClassLoader_Ext();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             String path = reader.readLine();
             //This class should be in your application class path
             Class<?> semaphoreClass = customClassLoader_ext.findClass(path);

             Object semaphore = semaphoreClass.newInstance();
             Method method = semaphoreClass.getMethod("lever");
             method.invoke(semaphore);
         }
     }

    @Override
    protected Class<?> findClass(String path) throws ClassNotFoundException {

        byte[] classData = null;

        try {
            classData = loadClassData(path);
            Class<?> c = defineClass(null, classData, 0, classData.length);
            //resolveClass(c);
            //classes.put(name, c);

            return c;
        } catch (IOException e) {
            throw new ClassNotFoundException("Class [" + path
                    + "] could not be found", e);
        }
    }

    /**
     * Load the class file into byte array
     *
     * @param path
     *            The name of the class e.g. com.codeslices.test.TestClass}
     * @return The class file as byte array
     * @throws IOException
     */
    private byte[] loadClassData(String path) throws IOException {

        File file = new File(path);

        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] classData = new byte[(int)length];

        // Считываем
        int offset = 0;

        int numRead = 0;

        while (offset < classData.length
                && (numRead=is.read(classData, offset, classData.length-offset)) >= 0) {

            offset += numRead;
        }

        // Проверяем, все ли прочитано
        if (offset < classData.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        // Закрываем и возвращаем
        is.close();

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
