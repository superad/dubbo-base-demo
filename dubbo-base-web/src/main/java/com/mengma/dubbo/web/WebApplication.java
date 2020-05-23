package com.mengma.dubbo.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author fgm
 * @description
 * @date 2020-05-23
 ***/
@SpringBootApplication
@PropertySource("classpath:/dubbo-consumer.properties")
@EnableDubbo(scanBasePackages = "com.mengma.dubbo.web.controller")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }


}
