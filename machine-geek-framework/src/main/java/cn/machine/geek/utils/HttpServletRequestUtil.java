package cn.machine.geek.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @Author: MachineGeek
 * @Description: HttpServletRequest请求工具类
 * @Date: 2020/11/6
 */
public class HttpServletRequestUtil {
    /**
    * @Author: MachineGeek
    * @Description: 获取用户的真实IP地址
    * @Date: 11:09 上午
     * @param request
    * @Return: java.lang.String
    */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x - forwarded - for");
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy - Client - IP");
        }
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL - Proxy - Client - IP");
        }
        if (ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取HTTP Body
    * @Date: 11:11 上午
     * @param request
    * @Return: java.lang.String
    */
    public static String getBody(HttpServletRequest request) {
        BufferedReader br = null;
        String str, wholeStr = "";
        try {
            br = request.getReader();
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wholeStr;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取请求参数
    * @Date: 11:14 上午
     * @param httpServletRequest
    * @Return: java.lang.String
    */
    public static String getParameter(HttpServletRequest httpServletRequest){
        String parameter = "?";
        Set<Map.Entry<String, String[]>> entries = httpServletRequest.getParameterMap().entrySet();
        for (Map.Entry<String,String[]> entry : entries){
            parameter = parameter + entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "&";
        }
        return parameter;
    }
}
