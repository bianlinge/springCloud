package com.dove.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class JsonDemo {
    @JSONField(ordinal = 3)
    private String name;
    @JSONField(ordinal = 2)
    private String address;
    @JSONField(ordinal = 1)
    private int age;

    @JSONField(ordinal = 4,format = "yyyy/MM/dd")//日期格式化
    //DD 一年中的天数  dd 每月中的天数
    private Date birth;


    public JsonDemo(final String name, final String address, final int age, final Date birth) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.birth = birth;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(final Date birth) {
        this.birth = birth;
    }

    public JsonDemo() {
    }

    @Override
    public String toString() {
        return "JsonDemo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                '}';
    }

    public static void main(String[] args) {
        JsonDemo jsonDemo = new JsonDemo();
        jsonDemo.setAddress("shengzhen");
        jsonDemo.setAge(12);
        jsonDemo.setName("tom");
        jsonDemo.setBirth(new Date());
        System.out.println(jsonDemo);
        //有序的序列化
        System.out.println("json: "+ JSON.toJSONString(jsonDemo));

        JSONObject o = (JSONObject) JSONObject.toJSON(jsonDemo);
        JsonDemo jsonDemo1 = JSONObject.toJavaObject(o, JsonDemo.class);
        System.out.println(jsonDemo1);
    }
}
