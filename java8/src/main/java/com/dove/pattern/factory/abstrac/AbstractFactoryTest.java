package com.dove.pattern.factory.abstrac;

import com.dove.pattern.factory.Milk;

public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new MilkFactory();
        Milk mengniu = abstractFactory.getMengniu();
        Milk telunsu = abstractFactory.getTelunsu();
        Milk yili = abstractFactory.getYili();
        System.out.println(mengniu.getName());
    }
}
