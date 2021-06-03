package com.dove.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class ConverterConfig extends WebMvcConfigurationSupport {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("---------------------");
        converters.add(converter());
   /*     converters.add(0, new MappingJackson2HttpMessageConverter() {
            @Override
            public ObjectMapper getObjectMapper() {
                super.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
                return super.getObjectMapper();
            }
        });*/
    }

    /*
   配置自定义的HttpMessageConvert的Bean，在Spring MVC里注册HttpMessageConvert有两个方法：
   configureMessageConverts：重置会覆盖Spring MVC里注册的多个HttpMessageConvert。
   extendMessageConverters：仅添加一个自定义的HttpMessageConvert，不覆盖默认注册的HttpMessageConvert
   这里重写extendMessageConverters
   */
    @Bean
    public MyHttpMessageCovert converter() {
        return new MyHttpMessageCovert();
    }


}
