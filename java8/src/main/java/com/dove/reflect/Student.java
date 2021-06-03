package com.dove.reflect;

public class Student {

    //通过多个构造器不同的修饰符  不同的形参列表

    Student(String name) {
        System.out.println("用default修饰的Student的含有一个String参数的构造器:"+name);
    }
    public Student() {
        System.out.println("用public修饰的Student的无参构造器");
    }
    public Student(String name,int age) {
        System.out.println("用public修饰的Student的含有两个参数的构造器:"+name+age);
    }
    public Student(boolean sex) {
        System.out.println("用public修饰的Student的含有一个参数的构造器:"+sex);
    }
    protected Student(int age) {
        System.out.println("用protected修饰的Student的含有一个参数的构造器:"+age);
    }
    private Student(String name,int age,boolean sex) {
        System.out.println("用private修饰的Student的含有三个参数的构造器:"+name+age+sex);
    }

}
