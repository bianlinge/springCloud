package com.dove.classloader;

public class MyClassLoaderTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        MyClassLoader myClassLoader = new MyClassLoader("dove", "E:\\test\\");

        //指定父类加载器为自己myClassLoader
        MyClassLoader classLoader2 = new MyClassLoader(myClassLoader, "classLoader2", "E:\\test\\");

        //     Class<?> aClass = myClassLoader.loadClass("ClassDemo");
//        Class<?> aClass = myClassLoader.loadClass("com.dove.classloader.ClassDemo");

        //classLoader2 的父类加载器是myClassLoader   myClassLoader 的父类加载器是系统类加载器
        Class<?> aClass = classLoader2.loadClass("com.dove.classloader.ClassDemo");//A Demo classLoaderName: sun.misc.Launcher$AppClassLoader@18b4aac2
        Object o = aClass.newInstance();

    }
}
