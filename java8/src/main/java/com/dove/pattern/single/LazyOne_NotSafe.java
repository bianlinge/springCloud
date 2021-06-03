package com.dove.pattern.single;

/**
 * 懒汉式 线程不安全
 *
 * 在外部需要使用的时候才进行实例化
 *
 *
 */
public class LazyOne_NotSafe {
    private LazyOne_NotSafe(){}

    //静态的,内存的公共区域
    private static LazyOne_NotSafe lazyOne = null;

    public static LazyOne_NotSafe getInstance(){
        //没有初始化 进行初始化 并复制
        if (lazyOne== null) {
            //两个线程同时进入if为空 同时new 对象创建了不同的对象
            lazyOne = new LazyOne_NotSafe();
        }

        return lazyOne;
    }
}
