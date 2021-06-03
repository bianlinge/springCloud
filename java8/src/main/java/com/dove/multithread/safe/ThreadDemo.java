package com.dove.multithread.safe;

//单例  volatile demo
public class ThreadDemo {
    private static volatile ThreadDemo instance = null;

    private ThreadDemo() {
    }

    public static synchronized ThreadDemo getInstance() {
        if (instance == null) {
            //
            instance = new ThreadDemo();

        }
        return instance;
    }

    public static void main(String[] args) {
        ThreadDemo instance = getInstance();

    }
}
