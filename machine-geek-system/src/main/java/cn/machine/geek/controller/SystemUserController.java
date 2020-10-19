package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.service.ISystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 系统用户控制器
 * @Date: 2020/10/19
 */
@Api(tags = "系统用户接口")
@RestController
@RequestMapping(value = "/SystemUser")
public class SystemUserController {
    @Autowired
    private ISystemUserService systemUserService;

    @ApiOperation(value = "分页接口",notes = "系统用户分页查询")
    @GetMapping(value = "/list")
    public R list(@Validated @RequestBody PageRequest pageRequest){
        return R.ok(systemUserService.listByCondition(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }
}
