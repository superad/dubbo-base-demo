package com.mengma.dubbo.spi.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author fgm
 * @description
 * @date 2020-05-24
 ***/
public class IpUtils {

    public static String getUserRealIP(HttpServletRequest request) throws UnknownHostException {
        String ip = "";
        if  (request.getHeader("x-forwarded-for") == null)  {
            ip = request.getRemoteAddr();
        }  else  {
            ip = request.getHeader("x-forwarded-for");
        }
        if  ("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equalsIgnoreCase(ip))  {
            // 获取本机真正的ip地址
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        return ip;
    }
}
