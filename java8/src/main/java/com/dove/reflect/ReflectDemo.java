package com.dove.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> stuClass = Class.forName("com.dove.reflect.Student");
        //获取所以构造方法
        Constructor<?>[] constructors = stuClass.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

        //参数为长度可变的形参:获取指定的构造器  参数为指定构造器的形参类型对应的class对象
        Constructor<?> constructor = stuClass.getDeclaredConstructor(String.class);
        System.out.println(constructor);
        //无参构造器
        Constructor<?> constructor1 = stuClass.getConstructor(null);
        System.out.println(constructor1);

        Constructor<?> declaredConstructor = stuClass.getDeclaredConstructor(String.class, int.class, boolean.class);
        System.out.println(declaredConstructor);

        try {
            //prvicate 私有修饰的构造方法xxx.setAccessible(true) 是为了解除私有限定  Accessible 无障碍
            declaredConstructor.setAccessible(true);
            Object zhangsan = declaredConstructor.newInstance("张三", 12, true);
            Student zs = (Student) zhangsan;
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("============Fields=============");

        Class<?> thClass = Class.forName("com.dove.reflect.Teacher");

        Field[] declaredFields = thClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField + ": " + declaredField.getName());
        }

        //获取特定字段
        Field nameField = thClass.getField("name");
        System.out.println(nameField);
        Field ageField = thClass.getDeclaredField("age");
        System.out.println(ageField);
        /**
         * 为字段设置具体的值
         * 参数一:该类的对象
         * 参数二:为特定的属性赋值
         */
        Object object = thClass.getConstructor().newInstance();
        nameField.set(object, "李四");
        ageField.set(object, 12);
        System.out.println(object);

        System.out.println("=============method============");
        Class<?> emClass = Class.forName("com.dove.reflect.Employee");
        Method[] declaredMethods = emClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        /**
         * &#x90fd;&#x662f;public&#x516c;&#x5171;&#x7684; &#x4f1a;&#x5c06;&#x7236;&#x7c7b;&#xff08;Object&#xff09;&#x7c7b;&#x4e2d;&#x7684;&#x516c;&#x5171;&#x65b9;&#x6cd5;&#x4e5f;&#x8f93;&#x51fa;&#x3002;
         */
        System.out.println("=======public methods =======");
        Method[] methods = emClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        //3.获取特定的公有的方法：  只能是公共的
        //参数一:要获取的方法的名称,参数二:方法对应的形参列表的类型

        Method getweekMoney = emClass.getMethod("getweekMoney", String.class, double.class);
        System.out.println(getweekMoney);////输出：public void fanshe.Employee.getweekMoney(java.lang.String,double)

        Method getDayMoney = emClass.getDeclaredMethod("getDayMoney", String.class);
        System.out.println(getDayMoney);//protected void com.dove.reflect.Employee.getDayMoney(java.lang.String)

        Object o = emClass.getConstructor().newInstance();
        Employee employee = (Employee) o;
        employee.getDayMoney("day111");

        //参数一:要调用的对象 参数二:方法具体要求传递的值   返回值:为调用该方法后的返回值所对应的对象,如果没有返回值（void）,则对象为null

        getDayMoney.setAccessible(true);
        Object day222 = getDayMoney.invoke(o, "day222");
        System.out.println(day222);//输出方法的返回值 void

        Method methodMain = emClass.getMethod("main", String[].class);
        //参数一:对象类型 null 当调用的方法为静态时,此时第一个参数可以为null
        Object invoke = methodMain.invoke(null,  (Object)new String[]{"a", "b", "c", "d"});
        Object invoke1 = methodMain.invoke(null, new Object[]{new String[]{"a", "b", "c", "d"}});


        //通过Method对象调用指定方法
        //如果底层方法是静态的，那么可以忽略指定的 obj 参数。该参数可以为 null。

        Method getYearMoney = emClass.getDeclaredMethod("getYearMoney", int.class);
        getYearMoney.setAccessible(true);
        Object invoke2 = getYearMoney.invoke(o, new Integer(10));
    }
}
