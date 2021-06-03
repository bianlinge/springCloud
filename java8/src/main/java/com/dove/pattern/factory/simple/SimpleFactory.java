package com.dove.pattern.factory.simple;

import com.dove.pattern.factory.Mengniu;
import com.dove.pattern.factory.Milk;
import com.dove.pattern.factory.Telunsu;
import com.dove.pattern.factory.Yili;

/**
 * 简单工厂
 */
public class SimpleFactory {

    public Milk getMilk(String name){

        if (name.equals("特仑苏")) {
            return new Telunsu();
        }else
        if (name.equals("蒙牛")) {
            return new Mengniu();
        } else if (name.equals("伊利")) {
            return new Yili();
        } else {
            System.out.println("无法生产合适的牛奶");
            return null;
        }

    }
}
