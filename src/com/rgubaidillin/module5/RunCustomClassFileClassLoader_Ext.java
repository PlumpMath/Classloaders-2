package com.rgubaidillin.module5;

/**
 * Created by Renat_Gubaidullin on 8/13/2016.
 */
public class RunCustomClassFileClassLoader_Ext {
    public static void main(String args[]) throws Exception {
        CustomClassFileClassLoader_Ext customClassLoader = new CustomClassFileClassLoader_Ext();
        customClassLoader.startMultipleLoad();
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String path = reader.readLine();
        //This class should be in your application class path
        //Class<?> semaphoreClass = customClassLoader.findClass(path);

        //Object semaphore = semaphoreClass.newInstance();
        //Method method = semaphoreClass.getMethod("lever");
        //method.invoke(semaphore);

    }

}
