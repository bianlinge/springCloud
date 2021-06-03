package com.dove.thread.life;

/**
 * 阻塞状态
 */
public class BlockedThread implements Runnable {
    @Override
    public void run() {
        synchronized (BlockedThread.class) {
            //加了锁 但是不释放锁
            while (true) {
                WaitingTime.waitSecond(200);
            }
        }

    }
}
