package com.imodule.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
//@EnableDiscoveryClient向服务中心注册，并且注册了一个叫restTemplate的bean。

/**
 * 1.  客户端的负载均衡

 负载均衡可分为服务端负载均衡和客户端负载均衡，服务端负载均衡完全由服务器处理，客户端不需要做任何事情。
 而客户端负载均衡技术，客户端需要维护一组服务器引用，每次客户端向服务端发请求的时候，
 会根据算法主动选中一个服务节点。常用的负载均衡算法有： Round Robbin,  Random，Hash，StaticWeighted等。

 Spring 提供两辆种服务调度方式：Ribbon+restful和Feign。Ribbon就是一个基于客户端的负载均衡器， Ribbon提供了很多在HTTP和TCP客户端之上的控制.
 */
public class ServiceRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
//@ LoadBalanced注册表明，这个restRemplate是需要做负载均衡的
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    TestRibbonService testRibbonService;
    @RequestMapping("/")
    public String sayHello() {
        return testRibbonService.getHello();
    }
}
