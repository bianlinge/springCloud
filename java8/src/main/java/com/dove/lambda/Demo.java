package com.dove.lambda;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.BinaryOperator;

public class Demo {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        Runnable noArguments = () -> System.out.println("Hello World");

        Runnable noArguments1 = () -> {
            System.out.println("Hello World");
            System.out.println("Hello World");
        };

        ActionListener oneArgument = event -> System.out.println("button clicked");

        BinaryOperator<Long>
                add = (x, y) -> x + y;
        Long apply = add.apply(12L, 52L);
        System.out.println(apply);

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;



        Frame frame = new Frame("我的GUI");
        frame.setLayout(null);

        frame.setBounds(0,0, 300,400);
        Button button = new Button();
        button.setBounds(200, 100,100,20);
        button.setLabel(new String("this is a button".getBytes(), StandardCharsets.ISO_8859_1));

        String name = "click";
//        name = ""; 重新复制lamda表达式编译不通过 匿名内部类也不会通过
//        Lambda 表达式引用的是值，而不是变量。
//        Lambda 表达式中引用的局部变量必须是 final 或既成事实上的 final 变量。

        button.addActionListener(event-> System.out.println(name));


        frame.add(button);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
//        函数式接口;有且只有一个方法抽象方法的接口,可以有其他的方法.

        //不能编译通过 lambda表达式鼓励开发是为了获取值 而不是赋值 赋值可能会带来副作用 局部变量就必须是final才可以

       /* ActionEvent localEvent = null;
        button.addActionListener(event -> {
            localEvent = event;
        });*/
    }
}
