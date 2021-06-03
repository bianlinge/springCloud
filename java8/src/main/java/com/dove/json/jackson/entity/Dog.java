package com.dove.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dog {

    //@JsonProperty("dog_name")
    private String name = "旺财";
    // @JsonProperty("dog_age")
    private int age;

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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    //    public Dog() {
//    }
//在反序列化的过程中，Jackson会将json串中的name属性传递给dog_name参数, 把json串中的age属性传递给dog_age参数。
    @JsonCreator
    public Dog(@JsonProperty("dog_name") String dog_name, @JsonProperty("dog_age") int dog_age) {
        this.name = dog_name;
        this.age = dog_age;
    }

    public static void main(String[] args) throws JsonProcessingException {
        String dogStr = "\n" +
                "{\n" +
                "  \"dog_name\": \"小白\",\n" +
                "  \"dog_age\": 3\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        Dog dog = mapper.readValue(dogStr, Dog.class);
        System.out.println(dog);
    }
}
