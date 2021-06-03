package com.dove.pattern.single;

/**
 * 双检锁  饿汉式 和 懒汉式安全结合
 */
public class DoubleCheckSingleton {
    private DoubleCheckSingleton(){}

    private static  DoubleCheckSingleton instance = null;

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (instance) {
                instance = new DoubleCheckSingleton();
                return instance;
            }
        } else {
            return instance;
        }
    }
}
