package com.imodule.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestRibbonService {
    @Autowired
    RestTemplate restTemplate;

    public String getHello(){//此处访问Restful地址 应用名+方法名
        return restTemplate.getForObject("http://App/hello/",String.class);
    }
}
/*这里关键代码就是, restTemplate.getForObject方法会通过ribbon负载均衡机制， 自动选择一个Hello word服务，

这里的URL是“http://APP/"，其中的App是服务的名字，是你在配置文件中写的名字 区分大小写 而注册到服务中心的有两个App。 所以，这个调用本质是ribbon-service作为客户端根据负载均衡算法自主选择了一个作为服务端的App服务。然后再访问选中的App来执行真正的Hello world调用。*/