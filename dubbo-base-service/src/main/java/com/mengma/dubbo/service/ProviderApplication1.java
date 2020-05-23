package com.mengma.dubbo.service;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author fgm
 * @description  服务1
 * @date 2020-05-23
 ***/
public class ProviderApplication1 {


    private static String TERMINATE = "stop";

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        app.start();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!TERMINATE.equalsIgnoreCase(input)){
            System.out.println("输入命令: "+input);
            input=scanner.nextLine();
        }
        System.out.println("系统终止命令:"+input);

    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.mengma.dubbo.service.impl")
    @PropertySource("classpath:/dubbo-provider.properties")
    static class ProviderConfiguration{
        @Bean
        public RegistryConfig registryConfig(){
            RegistryConfig config=new RegistryConfig();
            config.setAddress("zookeeper://127.0.0.1:2181");
            return config;
        }



    }
}
