package com.rgubaidillin.module5;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Renat_Gubaidullin on 8/13/2016.
 */
public class CustomClassFileClassLoader extends ClassLoader{

    /**
     * The HashMap where the classes will be cached
     */
    //private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

    //@Override
   // public String toString() {
   //     return CustomClassLoader.class.getName();
   // }

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
