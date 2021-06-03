package com.dove.pattern.factory.simple;

import com.dove.pattern.factory.Milk;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimpleFactory factory = new SimpleFactory();

        Milk telunus = factory.getMilk("特仑苏");

        System.out.println(telunus.getName());
    }
}
