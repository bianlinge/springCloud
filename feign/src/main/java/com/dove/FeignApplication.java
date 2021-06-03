package com.dove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 各个微服务都是以HTTP接口的形式暴露自身服务的，因此在调用远程服务时就必须使用HTTP客户端。
 * Feign就是Spring Cloud提供的一种声明式REST客户端。
 * 可以通过Feign访问调用远端微服务提供的REST接口。
 * 现在我们就用Feign来调用SERVICE-HELLOWORLD暴露的REST接口，以获取到“Hello World”信息。
 * 在使用Feign时，Spring Cloud集成了Ribbon和Eureka来提供HTTP客户端的负载均衡。
 */
@SpringBootApplication
@EnableFeignClients  //支持Feign的客户端调用
//@EnableEurekaClient
@EnableSwagger2
public class FeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
