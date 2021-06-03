package com.dove.multithread.threadpool;


import java.util.Date;
import java.util.concurrent.*;

public class MyExecutors extends ThreadPoolExecutor {
    private ConcurrentHashMap<String, Date> timemap;
    public MyExecutors(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.timemap = new ConcurrentHashMap<String, Date>();
    }

    public MyExecutors(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        this.timemap = new ConcurrentHashMap<String, Date>();
    }

    public MyExecutors(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        this.timemap = new ConcurrentHashMap<String, Date>();
    }

    public MyExecutors(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        this.timemap = new ConcurrentHashMap<String, Date>();
    }

    //线程执行前
    @Override
    protected void beforeExecute(final Thread t, final Runnable r) {
        timemap.put(String.valueOf(r.hashCode()), new Date());

    }
    //线程执行后
    @Override
    protected void afterExecute(final Runnable r, final Throwable t) {
        //remove 返回value值 获取并删除
        Date startDate = timemap.remove(String.valueOf(r.hashCode()));
        long time =System.currentTimeMillis()- startDate.getTime();
        System.out.println("任务执行时间: "+time);
    }

    @Override
    public void shutdown() {
        System.out.println("已经执行的任务数: "+this.getCompletedTaskCount());
        System.out.println("正在主动执行任务的线程的大概数量: "+this.getActiveCount());
        System.out.println("核心线程数: "+this.getCorePoolSize());
        System.out.println("允许的最大线程数: "+this.getMaximumPoolSize());
        System.out.println("任务队列数量: "+this.getQueue().size());
        super.shutdown();
    }

    public static ExecutorService newCachedThreadPool() {
        return new MyExecutors(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }
}
