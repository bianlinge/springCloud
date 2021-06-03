package com.dove.multithread;

import sun.misc.Cache;

public class CashDemo {
    public static void main(String[] args) {
        Cache cache = new Cache();
        Object put = cache.put("1", "2");
        Object o = cache.get("1");
        System.out.println(o);
    }
}
