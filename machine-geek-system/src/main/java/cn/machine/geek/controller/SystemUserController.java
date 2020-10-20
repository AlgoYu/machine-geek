package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.entity.SystemUser;
import cn.machine.geek.service.ISystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @ApiOperation(value = "分页获取系统用户",notes = "分页获取系统用户")
    @GetMapping(value = "/list")
    public R list(@Validated PageRequest pageRequest){
        return R.ok(systemUserService.listByCondition(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }

    @ApiOperation(value = "增加系统用户",notes = "增加系统用户")
    @PostMapping(value = "/add")
    public R add(@RequestBody SystemUser systemUser){
        systemUser.setCreateTime(LocalDateTime.now());
        return R.ok(systemUserService.save(systemUser));
    }

    @ApiOperation(value = "根据ID删除系统用户",notes = "根据ID删除系统用户")
    @PutMapping(value = "/deleteById")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(systemUserService.removeById(id));
    }

    @ApiOperation(value = "根据ID更新系统用户",notes = "根据ID更新系统用户")
    @PutMapping(value = "/updateById")
    public R updateById(@RequestBody SystemUser systemUser){
        systemUser.setUpdateTime(LocalDateTime.now());
        return R.ok(systemUserService.updateById(systemUser));
    }

    @ApiOperation(value = "根据ID获取用户",notes = "根据ID获取系统用户")
    @GetMapping(value = "/getById")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemUserService.getById(id));
    }
}
