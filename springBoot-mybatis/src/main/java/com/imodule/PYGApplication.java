package com.imodule;

import com.imodule.dao.mapper.CategoryMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@MapperScan({"com.imodule.dao.mapper","com.imodule.dao.provider"})
public class PYGApplication {
    public static void main(String[] args) {
        SpringApplication.run(PYGApplication.class,args);
    }
}
