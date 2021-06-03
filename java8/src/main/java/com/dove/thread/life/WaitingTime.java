package com.dove.thread.life;

import java.util.concurrent.TimeUnit;

/**
 * TIME_WARTING 超时等待 10s后自行返回继续执行
 */
public class WaitingTime implements Runnable {
    @Override
    public void run() {

        while (true) {
            waitSecond(200);
        }

    }

    public static final void waitSecond(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
