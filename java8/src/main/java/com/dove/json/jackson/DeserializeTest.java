package com.dove.json.jackson;

import com.dove.json.jackson.entity.Person;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DeserializeTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //配置在反序列化过程中如果json字符串中存在无法匹配的属性不会失败
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //上面的configure(xxx,false) 等同于disable(xxx),例如下面这行和上面作用是一样的。
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Person person = mapper.readValue(new File("D:\\Backup\\桌面\\springCloud\\java8\\person.json"), Person.class);
        String jsonstr = "{\n" +
                "  \"name\" : \"zhangsan\",\n" +
                "  \"age\" : 11,\n" +
                "  \"birth\" : \"2010-10-10\",\n" +
                "  \"address\" : {\n" +
                "    \"homeAddress\" : \"New York\",\n" +
                "    \"workAddress\" : \"Tokyo\"\n" +
                "  },\n" +
                "  \"friends\" : [ \"weiying\", \"caifang\" ],\n" +
                "  \"info\" : {\n" +
                "    \"height\" : \"175cm\",\n" +
                "    \"weight\" : \"80kg\"\n" +
                "  }\n" +
                "}";
//        Person person = mapper.readValue(jsonstr, Person.class);
        System.out.println(person);

    }
}
