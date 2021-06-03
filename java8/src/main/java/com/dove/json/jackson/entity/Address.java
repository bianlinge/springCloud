package com.dove.json.jackson.entity;


public class Address{
    public String homeAddress;
    public String workAddress;

    public Address(String homeAddress, String workAddress) {
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "homeAddress='" + homeAddress + '\'' +
                ", workAddress='" + workAddress + '\'' +
                '}';
    }

    //必须提供一个无参的构造器
    public Address() {}
}

