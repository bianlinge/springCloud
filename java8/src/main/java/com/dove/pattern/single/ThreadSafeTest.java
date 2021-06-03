package com.dove.pattern.single;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ThreadSafeTest {
    public static void main(String[] args) {
        int count = 1000;
        CountDownLatch latch = new CountDownLatch(count);

        final Set<LazyOne_NotSafe> synset = Collections.synchronizedSet(new HashSet<>());

        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread(){
                @Override
                public void run() {
                    LazyOne_NotSafe instance = LazyOne_NotSafe.getInstance();
                    //LazyTwo_Syn instance = LazyTwo_Syn.getInstance();
                    System.out.println(instance);
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            latch.countDown();
        }

        long end = System.currentTimeMillis() - start;
        System.out.println("总耗时: "+end);

    }
}
