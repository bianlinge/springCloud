package com.dove.reflect;

import java.util.Arrays;

public class Employee {
    public Employee() {}

    public String name;

    protected double money;

    String address;

    private long number;


    protected void getDayMoney(String name) {
        System.out.println("我是Employee受保护的获取日薪的方法,有一个参数为:"+name);
    }

    public void getweekMoney(String name,double money) {
        System.out.println("我是Employee公有的获取周薪的方法,没有参数..");
    }

    void getMonthMoney() {
        System.out.println("我是Employee默认的获取月薪的方法,没有参数..");
    }

    private void getYearMoney(int age) {
        System.out.println("我是Employee私有的的获年薪月薪的方法,有一个参数为:"+age);
    }

    public static void main(String[] args) {
        System.out.println("Employee中的main()方法执行了...");
        System.out.println(Arrays.toString(args));
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", money=" + money + ", address=" + address + ", number=" + number + "]";
    }
}
