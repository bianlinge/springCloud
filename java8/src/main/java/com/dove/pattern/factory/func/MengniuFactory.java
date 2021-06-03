package com.dove.pattern.factory.func;

import com.dove.pattern.factory.Mengniu;
import com.dove.pattern.factory.Milk;

public class MengniuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Mengniu();
    }
}
