package com.imodule.stream;

public class Person {
    private String name;
    private Integer age;
    private Integer order;

    public Person(String name, Integer age, Integer order) {
        this.name = name;
        this.age = age;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", order=" + order +
                '}';
    }
}
