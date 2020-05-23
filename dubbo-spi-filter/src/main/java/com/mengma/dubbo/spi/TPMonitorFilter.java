package com.mengma.dubbo.spi;

import com.mengma.dubbo.spi.utils.MonitorWaterLineCalculator;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.rpc.*;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author fgm
 * @description  tp monitor
 * @date 2020-05-24
 ***/
public class TPMonitorFilter implements Filter,Runnable {
    private static MonitorWaterLineCalculator WATERLINE_90=new MonitorWaterLineCalculator(90);
    private static MonitorWaterLineCalculator WATERLINE_99=new MonitorWaterLineCalculator(99);
    private static final Logger logger = LoggerFactory.getLogger(TransportIPFilter.class);


    public TPMonitorFilter(){
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(this,1,5, TimeUnit.SECONDS);
    }


    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long  start= System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        //耗时时间
        long cost = System.currentTimeMillis()-start;
        WATERLINE_90.accumulate(cost);
        WATERLINE_99.accumulate(cost);
        return result;

    }

    @Override
    public void run() {

       double waterLine90=WATERLINE_90.getResult();
       double waterLine99=WATERLINE_99.getResult();

        logger.info("waterLine90 is :"+waterLine90);
        logger.info("waterLine99 is :"+waterLine99);




    }
}
