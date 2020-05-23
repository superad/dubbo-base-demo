package com.mengma.dubbo.service.impl;

import com.mengma.dubbo.service.HelloService;
import com.mengma.dubbo.service.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.common.config.ConfigurationUtils;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Random;

/**
 * @author fgm
 * @description
 * @date 2020-05-23
 ***/
@Service
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    private Random random=new Random();

    @Override
    public String hello(String name) {

        logger.info("Thread name:"+Thread.currentThread().getName());
        Object ip=RpcContext.getContext().getAttachment(Constants.REQUEST_IP);
        if(null!=ip){
            logger.info("request ip is :"+ip);
        }
        return "hello "+name;
    }

    @Override
    public String methodA() {
        sleep();
        return "successA";
    }

    private void sleep() {
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String methodB() {
        sleep();
        return "successB";
    }

    @Override
    public String methodC() {
        sleep();
        return "successC";
    }
}
