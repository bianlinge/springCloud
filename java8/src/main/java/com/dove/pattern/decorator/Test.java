package com.dove.pattern.decorator;

/**
 * Created by E1041 on 2020/4/14.
 */
public class Test {
    public static void main(String[] args) {
        Food food = new Bread(new Vegetable(new Cream(new Food("香肠"))));
        System.out.println(food.make());
    }
    //装饰着模式 所有组件都继承同一组件  并且每个组件都持有父类和包含父类的构造方法
	//套娃模式 递归
}
