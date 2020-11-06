package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 异常控制器
 * @Date: 2020/11/6
 */
@Api(tags = "异常接口")
@RestController
@RequestMapping(value = "/api/exception/")
public class ErrorController {

    @ApiOperation(value = "增加一条异常",notes = "使用的是1除以0异常")
    @PutMapping(value = "/addException")
    public R addException(){
        return R.ok(1 / 0);
    }
}
