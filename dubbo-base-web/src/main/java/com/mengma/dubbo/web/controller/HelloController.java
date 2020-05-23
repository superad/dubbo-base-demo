package com.mengma.dubbo.web.controller;

import com.mengma.dubbo.service.HelloService;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fgm
 * @description
 * @date 2020-05-23
 ***/
@RestController
@RequestMapping
public class HelloController {

    private volatile boolean stop = false;
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);


    @Reference(check = false,timeout = 3000)
    private HelloService helloService;

    @GetMapping(value = {"/","/hello"})
    public String hello(){
        return helloService.hello("mengma!");
    }

    @GetMapping("/waterLine/stop")
    public String waterLineStop(){
        this.stop=true;
        return "stop success!";

    }


    @GetMapping("/waterLine/start")
    public String waterLineStart(){

        for(int i=0;i<10;i++){
            Thread threadA=new Thread(() -> {
                while(!stop){
                    helloService.methodA();
                }
                logger.info("stop value is :"+stop);
            });
            Thread threadB=new Thread(() -> {
                while(!stop){
                    helloService.methodB();
                }
                logger.info("stop value is :"+stop);
            });
            Thread threadC=new Thread(() -> {
                while(!stop){
                    helloService.methodC();
                }
                logger.info("stop value is :"+stop);

            });
            threadA.start();
            threadB.start();
            threadC.start();
        }

        return "start success!";

    }


}
