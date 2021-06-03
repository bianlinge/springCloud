package com.dove.fanxing;

import java.util.ArrayList;

public class FanxingTest {

    /*此处的？和Number、String、Integer一样都是一种实际的类型，
    可以把？看成所有类型的父类。是一种真实的类型*/
    private static void showKeyValue1(Generic<?> obj){
        System.out.println("泛型测试 key value is " + obj.getKey());
    }

    public static void main(String[] args) {
    /*    ArrayList arrayList = new ArrayList();

        arrayList.add("ddd");
        arrayList.add(111);

        System.out.println(arrayList);*/

      //  Integer extends Number
        Generic<Integer> gInteger = new Generic<Integer>(123);
        Generic<Number> gNumber = new Generic<Number>(456);

        showKeyValue1(gNumber);
        //showKeyValue1(gInteger);

        showKeyValue1(gInteger);

    }

}
