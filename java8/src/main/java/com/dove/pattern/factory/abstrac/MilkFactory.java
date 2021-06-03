package com.dove.pattern.factory.abstrac;

import com.dove.pattern.factory.Mengniu;
import com.dove.pattern.factory.Milk;
import com.dove.pattern.factory.Telunsu;
import com.dove.pattern.factory.Yili;
import com.dove.pattern.factory.func.MengniuFactory;
import com.dove.pattern.factory.func.TelunsuFactory;
import com.dove.pattern.factory.func.YiliFactory;

public class MilkFactory extends AbstractFactory {

    @Override
    public Milk getYili() {
        return new Yili();
        //return new YiliFactory().getMilk();
    }

    @Override
    public Milk getTelunsu() {
        return new Telunsu();
//        return new TelunsuFactory().getMilk();
    }

    @Override
    public Milk getMengniu() {
        return new Mengniu();
//        return new MengniuFactory().getMilk();
    }
}
