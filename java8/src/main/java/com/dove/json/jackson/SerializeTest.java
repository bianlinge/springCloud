package com.dove.json.jackson;

import com.dove.json.jackson.entity.Address;
import com.dove.json.jackson.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerializeTest {
    public static void main(String[] args) throws ParseException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Address address = new Address("New York", "Tokyo");
        Person person = new Person("zhangsan", 11, address, new Date(), "weiying", "caifang");
        person.info.put("height", "175cm");
        person.info.put("weight", "80kg");

        ObjectMapper mapper = new ObjectMapper();
        //按照map的key的自然排序来产生序列化结果
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        //配置序列化的输出缩进  是否格式化
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        //序列化日期  没有设置日期格式 默认序列化为毫秒值"birth" : 1286640000000,
        mapper.setDateFormat(format);


        String value = mapper.writeValueAsString(person);
        System.out.println(value);
        //序列化的过程就这一行代码，当然也可以选择输出到文件或其他流中
        mapper.writeValue(new File("D:\\Backup\\桌面\\springCloud\\java8\\person.json"), person);
    }
}
