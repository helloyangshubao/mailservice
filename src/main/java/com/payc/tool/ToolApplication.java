package com.payc.tool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author yangshubao
 * Created on 2022/11/22
 */

@SpringBootApplication
@EnableScheduling
@MapperScan("com.payc.tool.dao")
@EnableAsync
@EnableRetry
public class ToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolApplication.class, args);
    }


}
