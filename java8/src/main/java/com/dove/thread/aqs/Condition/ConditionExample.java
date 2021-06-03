package com.dove.thread.aqs.Condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionExample {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            reentrantLock.lock();
            try {
                log.info("wait signal...");// setp1
                condition.await();
                log.info("get signal"); // setp4
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                log.info("get lock");// setp2
                Thread.sleep(3000);
                condition.signal();
                log.info("send signal ~ ");// setp3
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }


        }).start();
    }
}
