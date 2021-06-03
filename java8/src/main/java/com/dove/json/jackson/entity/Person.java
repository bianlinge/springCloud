package com.dove.json.jackson.entity;


import java.util.*;

public class Person {
    private String name;
    private int age;
    public Date birth;
    private Address address;
    private List<String> friends = new ArrayList<>();
    public Map<String, String> info = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("name: " + this.name + "\n");
        sb.append("age: " + this.age + "\n");
        sb.append("address: " + this.address + "\n");
        sb.append("birth: " + this.birth + "\n");
        this.friends.forEach(x -> sb.append("friend:" + x + "\n"));

        return sb.toString();
    }

    public Person(String name, int age, Address address, Date birth, String... friends) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birth = birth;
        this.friends.addAll(Arrays.asList(friends));
    }

///注意这个默认构造器，如果没有默认的构造器，应该有一个@JsonCreator修饰的构造器
    public Person() {
    }
}
