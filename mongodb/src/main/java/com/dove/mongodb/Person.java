package com.dove.mongodb;

import org.mongodb.morphia.annotations.Id;

public class Person {
    @Id
    private String id;
    private String name;
    private String age;
    private String sex;
    private String phone;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(final String age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
