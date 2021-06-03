
package com.dove.config;
/*

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**为了打印请求接口日志
 * Created by E1026 on 2018/9/12.
 */

import com.dove.config.FeignInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
  /*  @Bean
    public Encoder feignFormEncoder() {
        return (Encoder) new FeignSpringFormEncoder(new SpringEncoder(messageConverters));
    }*/

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }


/**
     * feign请求拦截器
     * @return
     */

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignInterceptor();
    }
}
