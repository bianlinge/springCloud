package com.dove.web.config;

public class Dogg extends CommonResult {
    private String name;
    private int age;

    public String getName() {
        return this.name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge( int age) {
        this.age = age;
    }

    public Dogg(String name,  int age) {
        this.name = name;
        this.age = age;
    }

    public Dogg() {
    }

}
