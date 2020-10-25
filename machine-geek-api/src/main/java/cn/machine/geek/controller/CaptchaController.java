package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import cn.machine.geek.service.IGraphCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 验证码控制器
 * @Date: 2020/10/25
 */
@Api(tags = "验证码接口")
@RestController
@RequestMapping(value = "/api/catpcha/")
public class CaptchaController {
    @Autowired
    private IGraphCaptchaService graphCaptchaService;

    @ApiOperation(value = "获取图形验证码",notes = "获取图形验证码")
    @GetMapping(value = "/getGraphCaptcha")
    public R getGraphCaptcha(String key){
        return R.ok(graphCaptchaService.createCaptcha(key));
    }
}
