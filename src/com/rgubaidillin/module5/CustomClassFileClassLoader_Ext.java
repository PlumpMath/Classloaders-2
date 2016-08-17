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

    /**
     * The HashMap where the classes will be cached
     */
    //private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

    //@Override
   // public String toString() {
   //     return CustomClassLoader.class.getName();
   // }
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


    //public static void main(String[] args) throws Exception {
    /*public void startMultipleLoad() throws Exception{
        do {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String path = reader.readLine();
            Class<?> semaphoreClass = new CustomClassFileClassLoader_Ext().loadClass(path);
            Object semaphore = semaphoreClass.newInstance();
            //Object semaphore = new CustomClassFileClassLoader_Ext().loadClass(path).newInstance();
            Method method = semaphoreClass.getMethod("lever");
            method.invoke(semaphore);
            //System.out.println("LOADED: " + foo); // Overload MyFoo#toString() for effect
            //System.out.println("Press <ENTER> when MyFoo.class has changed");
            //System.in.read();
        } while (true);
    }
    */

    @Override
    protected Class<?> findClass(String path) throws ClassNotFoundException {

        //if (classes.containsKey(name)) {
        //    return classes.get(name);
        //}

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
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));;
        //ByteArrayOutputStream out = new ByteArrayOutputStream();

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
    /*public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException,
            InvocationTargetException
    {
        CustomClassLoader loader = new CustomClassLoader();
        // This class should be in your application class path
        Class<?> c = loader.findClass("net.codeslices.test.TestClass");
        Object o = c.newInstance();
        Method m = c.getMethod("toString");
        System.out.println(m.invoke(o));
    }
    */
}
