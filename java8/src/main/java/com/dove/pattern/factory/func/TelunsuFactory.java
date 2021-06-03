package com.dove.pattern.factory.func;

import com.dove.pattern.factory.Milk;
import com.dove.pattern.factory.Telunsu;

public class TelunsuFactory implements Factory{

    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}
