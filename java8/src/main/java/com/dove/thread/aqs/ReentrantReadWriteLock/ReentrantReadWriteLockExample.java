package com.dove.thread.aqs.ReentrantReadWriteLock;

import jdk.nashorn.internal.ir.CallNode;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReentrantReadWriteLockExample {
    class MyObject {
        private Object object;
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void getData() throws InterruptedException {
            readWriteLock.readLock().lock();//上读锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备读取数据");
                Thread.sleep(new Random().nextInt(1000));
                System.out.println(Thread.currentThread().getName() + "读数据为：" + this.object);
            } finally {
                readWriteLock.readLock().unlock();
            }

        }

        public void putData(Object object) {
            readWriteLock.writeLock().lock();
            try {

                System.out.println(Thread.currentThread().getName() + "准备写数据");
                Thread.sleep(new Random().nextInt(1000));
                this.object = object;
                System.out.println(Thread.currentThread().getName() + "写数据为：" + this.object);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        final MyObject myObject = new ReentrantReadWriteLockExample().new MyObject();
        ExecutorService executorService = Executors.newCachedThreadPool();

        //线程 4 5 6 读数据
        for (int i = 0; i < 3; i++) {
            executorService.execute(()->{
              /*  for (int i1 = 0; i1 < 3; i1++) {
                    myObject.putData(new Random().nextInt(1000));//写操作
                }*/
                myObject.putData(new Random().nextInt(1000));//写操作
            });
        }

        //线程1 2 3 写数据
        for (int i = 0; i < 3; i++) {
            executorService.execute(()->{
              /*  for (int i1 = 0; i1 < 3; i1++) {
                    try {
                        myObject.getData();//写操作
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                try {
                    myObject.getData();//写操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
//从数据中也可以发现一开始读取的数据可能不一样，但是你会发现下面的时候线程4和线程5、线程6之间的读取的数据都是一样的，这就是共享读的特性。
//读写锁是在重入锁ReentrantLock基础上的一大改造，其通过在重入锁上维护一个读锁一个写锁实现的。
// 对于ReentrantLock和ReentrantreadWriteLock的使用需要在开发者自己根据实际项目的情况而定。
// 对于读写锁当读的操作远远大于写操作的时候会增加程序很高的并发量和吞吐量。虽说在高并发的情况下，
// 读写锁的效率很高，但是同时又会存在一些问题，比如当读并发很高时读操作长时间占有锁，
// 导致写锁长时间无法被获取而导致的线程饥饿问题，
// 因此在JDK1.8中又在ReentrantReadWriteLock的基础上新增了一个读写并发锁StampLock。