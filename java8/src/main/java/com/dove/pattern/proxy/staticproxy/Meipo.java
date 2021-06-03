package com.dove.pattern.proxy.staticproxy;

public class Meipo {
    private Person person;

    public Meipo(Person person) {
        this.person = person;
    }

    public void findGF(){
        System.out.println("找到对象....");
    }
}
