package com.rgubaidillin.module5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * Created by Renat_Gubaidullin on 8/13/2016.
 */
public class RunJavaFileClassloader {
    public static void main(String args[]) throws Exception {
        CustomJavaFileClassLoader customClassLoader = new CustomJavaFileClassLoader();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        // This class should be in your application class path
        Class<?> semaphoreClass = customClassLoader.findClass(path);

        Object semaphore = semaphoreClass.newInstance();
        Method method = semaphoreClass.getMethod("lever");
        method.invoke(semaphore);

    }

}
