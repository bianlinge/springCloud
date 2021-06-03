package com.dove.pattern.single;

import com.sun.org.apache.xpath.internal.objects.XNull;

/**
 * 静态内部类 安全
 * 首先初始化内部类LazyHolder, 然后初始化LazyThree_InnerClass
 * 如果没有使用内部类 内部类就不加载; 也属于懒汉式的一种
 *
 */
public class LazyThree_InnerClass {

    private LazyThree_InnerClass() {
    }

    public static final LazyThree_InnerClass getInstance() {
        return LazyHolder.Lazy;
    }
    private static class LazyHolder{
        private static final  LazyThree_InnerClass Lazy = new LazyThree_InnerClass();
    }
}
