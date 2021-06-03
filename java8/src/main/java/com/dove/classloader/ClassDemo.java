package com.dove.classloader;

public class ClassDemo {
    public ClassDemo() {
        System.out.printf("A Demo classLoaderName: %s", ClassDemo.class.getClassLoader());
    }
}
