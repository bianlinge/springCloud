package com.dove.classloader;

import java.util.HashMap;

/**
 * Create by Dove on 2019/9/29 1:30
 *
 * @author Administrator
 */
public class ClassLoadSort {
    /*  第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)，
       第二层括弧实际上是一个实例初始化块 (instance initializer block)，
      这个块在内部匿名类构造时被执行。这个块之所以被叫做“实例初始化块”是因为它们被定义在了一个类的实例范围内*/
    private static HashMap<String, String> map = new HashMap<String, String>() {
        {
            put("name", "June");
            put("qq", "2572073701");
        }

    };

    static {
        System.out.println("1.>>>>>>>Static block called：静态块被调用 并只执行一次");
    }

    {
        System.out.println("2.>>>>>>>Instance initializer called：实例初始化块被调用");
    }

    public ClassLoadSort() {
        System.out.println("3.>>>>>>>Constructor called：构造器被调用");
    }


    public static void main(String[] args) {
       System.out.println("静态实例map:" + map.get("name"));

        new ClassLoadSort();//实例化
        System.out.println("=======================================");
        new ClassLoadSort();
    }


//打印结果：
//    1.>>>>>>>Static block called：静态块被调用 并只执行一次
//    静态实例 map:June
//    2.>>>>>>>Instance initializer called：实例初始化块被调用
//    3.>>>>>>>Constructor called：构造器被调用
//      =======================================
//   2.>>>>>>>Instance initializer called：实例初始化块被调用
//   3.>>>>>>>Constructor called：构造器被调用

}
