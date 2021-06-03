package com.dove.json.serializable;

import java.io.Serializable;

public class Person extends Man implements Serializable {
    private String name;
    private int age;

    private static int num = 100;

    public String getName() {
        return this.name;
    }

    public Person( final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public static int getNum() {
        return Person.num;
    }

    public static void setNum(final int num) {
        Person.num = num;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}'+">>>>"+this.getAddress()+"<<<<<"+num;
    }
}
