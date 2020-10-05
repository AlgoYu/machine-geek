package cn.machine.geek.controller;

import cn.machine.geek.entity.R;
import io.swagger.annotations.Api;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 测试类
 * @Date: 2020/10/6
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping(value = "/test")
@Secured(value = "ROLE_ADMINISTRATOR")
public class TestController {
    @PreAuthorize("hasAuthority('SYSTEM')")
    @GetMapping(value = "/test")
    public R test(){
        return R.ok();
    }

    @PreAuthorize("hasAuthority('SYTEM')")
    @GetMapping(value = "/test2")
    public R test2(){
        return R.ok();
    }
}
