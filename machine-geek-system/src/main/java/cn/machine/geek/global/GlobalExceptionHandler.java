package cn.machine.geek.global;

import cn.machine.geek.dto.R;
import cn.machine.geek.utils.HttpServletRequestUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: MachineGeek
 * @Description: 全局异常处理
 * @Date: 2020/10/20
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R exceptionHandler(HttpServletRequest httpServletRequest, Exception e){
        System.out.println(httpServletRequest.getRequestURI());
        System.out.println(httpServletRequest.getMethod());
        if(httpServletRequest.getMethod().equals("GET") || httpServletRequest.getMethod().equals("DELETE")){
            System.out.println(HttpServletRequestUtil.getParameter(httpServletRequest));
        }else{
            System.out.println(HttpServletRequestUtil.getBody(httpServletRequest));
        }
        System.out.println(e.getClass().getName());
        System.out.println(e.getMessage());
        return R.fail(e.getMessage());
    }
}
