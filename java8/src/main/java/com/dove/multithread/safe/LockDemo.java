package com.dove.multithread.safe;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    static int count = 0;

    static Lock lock = new ReentrantLock();
    public static void incr(){
        try {
            Thread.sleep(1);
            lock.lock();
            count++;
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
