package com.dove.pattern.factory.func;

import com.dove.pattern.factory.Milk;

public class FactoryTest {
    public static void main(String[] args) {
        Factory yiliFactory = new YiliFactory();
        Milk milk = yiliFactory.getMilk();
        System.out.println(milk.getName());
    }
}
