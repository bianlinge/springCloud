package com.dove.pattern.factory.func;

import com.dove.pattern.factory.Milk;
import com.dove.pattern.factory.Yili;

public class YiliFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Yili();
    }
}
