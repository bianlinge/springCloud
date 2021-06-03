package com.dove.classloader;

public class Demo {
    public static int tmp = 1;
    static {
        tmp = 2;
        System.out.println("static{} "+tmp);
    }
    public static void main(String[] args) {
        tmp = 3;
        System.out.println("main{} "+tmp);
        System.out.println(Demo.class.getClassLoader());

        ClassLoader classLoader = Demo.class.getClassLoader();
        ClassLoader parent = classLoader.getParent();
        ClassLoader parent1 = parent.getParent();

        System.out.println(parent);
        System.out.println(parent1);
    }
}
