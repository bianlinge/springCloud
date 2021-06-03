package com.dove.multithread.safe;

// synchronized
public class Atomic_Synchronized {

    private static int count;
    public synchronized void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;

        String name = Thread.currentThread().getName();
        System.out.println(name + "->count:" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(AtomicDemo::inc);
            thread.start();
        }

        Thread.sleep(4000);
        Thread mainthread = Thread.currentThread();
        System.out.println(mainthread.getName());
        System.out.println("FINAL=====>" + count);

    }



/*    private static int count;

    public  void inc()  {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized(this){
            count++;
        }
        String name = Thread.currentThread().getName();
        System.out.println(name +"->count:"+count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            Thread thread = new Thread(AtomicDemo::inc);
            thread.start();
        }
        Thread.sleep(4000);
        System.out.println("FAINAL=====>"+count);

    }*/
}
