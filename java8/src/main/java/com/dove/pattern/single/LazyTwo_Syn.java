package com.dove.pattern.single;

/**
 * 懒汉式 线程安全
 */
public class LazyTwo_Syn {
    private LazyTwo_Syn(){}

    private static LazyTwo_Syn instance = null;
    public static synchronized LazyTwo_Syn getInstance(){
        if (instance == null) {
            instance = new LazyTwo_Syn();
            return instance;
        } else {
            return instance;
        }
    }
}
