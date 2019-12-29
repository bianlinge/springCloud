package com.imodule.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @FeignClient用于通知Feign组件对该接口进行代理(不需要编写接口实现)，
 * 使用者可直接通过@Autowired注入; 该接口通过value定义了需要调用的App服务（通过服务中心自动发现机制会定位具体URL）;
 * @RequestMapping定义了Feign需要访问的App服务的URL（本例中为根“/hello”）
 */
@FeignClient(value = "App",fallback = HelloWorldServiceFailure.class,configuration = FeignInterceptor.class)
@Component
public interface HelloWorldService {
    @GetMapping("/hello")
    String sayHello();
}
