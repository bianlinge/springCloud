package com.dove.multithread.safe;

import java.util.Currency;

//原子性问题
public class AtomicDemo {
    private static int count;

    public static void inc()  {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        System.out.println(Thread.currentThread().getName()+"->count:"+count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(AtomicDemo::inc);
            thread.start();
        }
        Thread.sleep(4000);
        System.out.println("FINAL=====>"+count);
    }
}
