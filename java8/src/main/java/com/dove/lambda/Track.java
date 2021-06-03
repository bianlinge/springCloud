package com.dove.lambda;

public class Track {
    String name;
    Integer age;

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Track(final String name, final Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

}
