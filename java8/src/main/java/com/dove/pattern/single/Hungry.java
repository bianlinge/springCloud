package com.dove.pattern.single;

/**
 * 饿汉式： 使用前就已经new 出来了
 * 避免了线程安全问题
 * 类加载的时候就立即初始化 创建了单例对象
 *    优点:没有锁 执行效率高
 *    缺点:浪费内存
 *
 */
public class Hungry {

    private Hungry(){}

    //先静态 后动态
    //先属性 后方法
    //先上后下

    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        System.out.println(System.currentTimeMillis()+":"+hungry);
        return hungry;
    }
}
