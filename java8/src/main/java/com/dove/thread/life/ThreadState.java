package com.dove.thread.life;

public class ThreadState {
    public static void main(String[] args) {
        new Thread(new WaitingTime(),"waitingTimeThread").start();
        new Thread(new WaitingState(),"WaitingStateThread").start();

        new Thread(new BlockedThread(),"BlockedThread-01").start();
        new Thread(new BlockedThread(),"BlockedThread-02").start();


    }
}
