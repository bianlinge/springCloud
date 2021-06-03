package com.dove.thread.ThreadLocal;

public class ThreadLocalTest2 {
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) {
       // InheritableThreadLocal类存储本地变量时，子线程能够获取到父线程中设置的本地变量
        //在主线程中设置值
        threadLocal.set("ThreadLocalTest");
        //在子线程中获取值
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程获取值：" + threadLocal.get());
            }
        });
        //启动子线程
        thread.start();
        //在主线程中获取值
        System.out.println("主线程获取值：" + threadLocal.get());
    }
}
