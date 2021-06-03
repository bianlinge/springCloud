package com.dove.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo implements Runnable{
    // ExecutorService executorService = Executors.newFixedThreadPool(3);
    //自定义线程工具类监控线程
    static ExecutorService executorService =  MyExecutors.newCachedThreadPool();
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            executorService.execute(new ThreadPoolDemo());
        }

        executorService.shutdown();
    }


    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
