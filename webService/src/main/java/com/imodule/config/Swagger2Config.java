
package com.imodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket ccaApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("task")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imodule"))
                .paths((regex("/.*")))//过滤的接口
                .build()
                .apiInfo(taskApiInfo());
    }

    private ApiInfo taskApiInfo() {
        return new ApiInfoBuilder()
                .title("测试服务接口列表")//大标题
                .version("1.0")//版本
                .build();
    }
}

