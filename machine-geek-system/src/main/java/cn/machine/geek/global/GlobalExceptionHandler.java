package cn.machine.geek.global;

import cn.machine.geek.dto.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: MachineGeek
 * @Description: 全局异常处理
 * @Date: 2020/10/20
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public R errorHandle(Exception e){
        return R.fail(e.getMessage());
    }
}
