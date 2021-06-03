package com.dove.multithread.safe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLock {
    static Map<String, Object> cashMap = new HashMap<>();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static Lock read = readWriteLock.readLock();
    static Lock write = readWriteLock.writeLock();

    //读锁
    public static final Object get(String key) {
        try {
            read.lock();
            return cashMap.get(key);
        } finally {
            read.unlock();
        }
    }

    //写锁
    public static final Object write(String key) {
        try {
            write.lock();
            return cashMap.put(key,"aaa");
        } finally {
            write.unlock();
        }
    }
}
