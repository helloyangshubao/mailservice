package com.payc.tool.config;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author yangshbuao
 * @date 2021-05-10
 */
@EnableOpenApi
@Configuration
public class Swagger3Config {
    @Value(value = "${swagger.enabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.payc.tool.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("工具服务")
                .description("工具服务")
                .contact(new Contact("平安云厨","https://www.payc.com.cn","yangshubao911@payctop.cn"))
                .version("1.0")
                .build();
    }
}


