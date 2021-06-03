package com.dove.thread.ThreadLocal;

/**
 * 同一个ThreadLocal在父线程中设置值后，在子线程中是无法获取
 * 到这个值的，这个现象说明ThreadLocal中存储的本地变量不具有传递性
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {
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
