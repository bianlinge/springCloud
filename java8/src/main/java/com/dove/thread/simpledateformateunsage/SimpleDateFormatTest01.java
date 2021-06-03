package com.dove.thread.simpledateformateunsage;

import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DateFormat类中的Calendar对象被多线程共享，而Calendar对象本
 *  * 身不支持线程安全。
 */
public class SimpleDateFormatTest01 {

    private static final int EXECUTE_COUNT = 1000;
    private static final int THREAD_COUNT = 20;
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //使用ThreadLocal存储每个线程拥有的SimpleDateFormat对象的副本，能够有效的避免多线程造成的线程安全问题，使用
    //ThreadLocal解决线程安全问题的代码如下所示。
    private static ThreadLocal<DateFormat> threadLocal = new DateFormatThreadLocal();

    //线程安全
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static  org.joda.time.format.DateTimeFormatter datetimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);

        ExecutorService executorService = Executors.newCachedThreadPool();
        final  ReentrantLock reentrantLock = new ReentrantLock();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {

//                       simpleDateFormat.parse("2020-01-01");
                        //解决方法1
                      /*  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        simpleDateFormat.parse("2020-01-01");*/
                        //解决方法2
                       /* synchronized (simpleDateFormat) {
                            simpleDateFormat.parse("2020-01-01");
                        }*/
                        //解决方法3
                        /*reentrantLock.lock();
                        simpleDateFormat.parse("2020-01-01");*/
                        //解决方法4
//                        threadLocal.get().parse("2020-01-01");
//                        dateTimeFormatter.parse("2020-01-01");
                        datetimeFormatter.parseLocalDate("2020-01-01");
                        //解决方法5

                    }/* catch (ParseException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    } */catch (NumberFormatException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    }finally {
                        //：为防止程序抛出异常而导致锁不能被释放，一定要将释放锁的操作放到finally代码块中
//                        reentrantLock.unlock();
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");

    }

    private static class DateFormatThreadLocal extends ThreadLocal<DateFormat> {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    }
}
