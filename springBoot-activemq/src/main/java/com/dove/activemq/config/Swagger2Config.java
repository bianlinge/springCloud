
package com.dove.activemq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket ccaApi() {
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> aParameters  = new ArrayList<>();
        tokenPar.name("token").description("令牌")
                .modelRef(new ModelRef(Swagger2Config.STRING))
                .parameterType("header")
                .required(false)//header中的ticket参数非必填，传空也可以
                .build();
        aParameters.add(tokenPar.build());//根据每个方法名也知道当前方法在设置什么参数
        //添加head参数配置end
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(aParameters)
                .groupName("webtask")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dove.activemq"))
                .paths((regex("/.*")))//过滤的接口
                .build()
                .apiInfo(taskApiInfo());
    }

    private ApiInfo taskApiInfo() {
        return new ApiInfoBuilder()
                .title("activemq测试服务接口列表")//大标题
                .version("1.0")//版本
                .build();
    }
}

