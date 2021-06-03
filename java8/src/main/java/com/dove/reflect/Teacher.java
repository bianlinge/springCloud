package com.dove.reflect;

public class Teacher {
    public Teacher() {
    }

    public String name;

    protected int age;

    boolean sex;

    private String address;

    @Override
    public String toString() {
        return "Teacher [name=" + name + ", age=" + age + ", sex=" + sex + ", address=" + address + "]";
    }
}
