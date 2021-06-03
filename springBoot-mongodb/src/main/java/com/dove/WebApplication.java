package com.dove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by E1041 on 2020/3/16.
 */
@SpringBootApplication
@EnableSwagger2
public class WebApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class,args);
	}
}
