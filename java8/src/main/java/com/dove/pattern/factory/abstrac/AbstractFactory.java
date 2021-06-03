package com.dove.pattern.factory.abstrac;

import com.dove.pattern.factory.Milk;

/**
 * 抽象工厂  可以生产任何产品
 */
public abstract class AbstractFactory {

    public abstract Milk getYili();

    public abstract Milk getTelunsu();

    public abstract Milk getMengniu();
}
