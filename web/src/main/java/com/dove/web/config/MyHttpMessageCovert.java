package com.dove.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class MyHttpMessageCovert extends AbstractHttpMessageConverter<DemoObj> {

    public MyHttpMessageCovert() {
        //新建一个我们自定义的媒体类型application/x-wisely
        super(new MediaType("application", "x-dove",Charset.forName("UTF-8")));
    }

    /**
     * 表明HttpMessageConvert只处理DemoObj这个类
     */
    @Override
    public boolean supports(Class aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 重写readInternal方法，处理请求的数据。代表我们处理由"-"隔开的数据，并转成DemoObj对象
     */
    @Override
    protected DemoObj readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {

        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        List<String> list = httpInputMessage.getHeaders().get("Content-Type");
        System.out.println(list.get(0));
        System.out.println("+++++++++++++++++++");
        System.out.println(temp);
        String[] tempArr = temp.split("-");
        return new DemoObj((tempArr[0]), tempArr[1]);
    }

    /**
     * 重写writeInternal，处理如何输出数据到response。此例中，在原样输出签名加上“hello:”
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + obj.getId() + "-"
                + obj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }


    /*
   配置自定义的HttpMessageConvert的Bean，在Spring MVC里注册HttpMessageConvert有两个方法：
   configureMessageConverts：重置会覆盖Spring MVC里注册的多个HttpMessageConvert。
   extendMessageConverters：仅添加一个自定义的HttpMessageConvert，不覆盖默认注册的HttpMessageConvert
   这里重写extendMessageConverters
   */

   /* public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MyHttpMessageCovert converter(){
        return new MyHttpMessageCovert();
    }*/

}
