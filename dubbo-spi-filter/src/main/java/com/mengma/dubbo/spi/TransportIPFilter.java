package com.mengma.dubbo.spi;

import com.mengma.dubbo.service.constants.Constants;
import com.mengma.dubbo.spi.utils.IpUtils;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.rpc.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

/**
 * @author fgm
 * @description  传输IP过滤器
 * @date 2020-05-24
 ***/
public class TransportIPFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TransportIPFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        fullFillIp();
        Result result = invoker.invoke(invocation);
        return result;
    }

    private void fullFillIp() {
        if(RequestContextHolder.getRequestAttributes()==null){
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String ip=IpUtils.getUserRealIP(request);
            RpcContext.getContext().setAttachment(Constants.REQUEST_IP,ip);
        } catch (UnknownHostException e) {
            logger.error("getUserRealIP ex"+ e.getCause());
        }
    }

    // public static String get

}
