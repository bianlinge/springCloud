package com.dove.json.serializable;

//java.io.NotSerializableException
public class JavaSerialiazbleTest {
    public static void main(String[] args) {
//        Person tom = new Person("tom", 12);

        Person tom = new Person("tom", 12);
        tom.setAddress("111111111111111");
        JavaSerializable javaSerializable = new JavaSerializable();
        byte[] bytes = javaSerializable.enSerializable(tom);

        Person person= (Person) javaSerializable.deSerializable(bytes, Person.class);
        System.out.println(person);

    }
}
