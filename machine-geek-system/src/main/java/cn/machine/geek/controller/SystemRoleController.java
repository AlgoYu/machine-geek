package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.entity.SystemRole;
import cn.machine.geek.service.ISystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统角色控制器
 * @Date: 2020/10/24
 */
@Api(tags = "系统角色接口")
@RestController
@RequestMapping(value = "/systemRole")
public class SystemRoleController {
    @Autowired
    private ISystemRoleService systemRoleService;

    @ApiOperation(value = "分页获取系统角色",notes = "分页获取系统角色")
    @GetMapping(value = "/list")
    public R list(@Validated PageRequest pageRequest){
        return R.ok(systemRoleService.listByCondition(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }

    @ApiOperation(value = "增加系统角色",notes = "增加系统角色")
    @PostMapping(value = "/add")
    public R add(@RequestBody SystemRole systemRole){
        systemRole.setCreateTime(LocalDateTime.now());
        return R.ok(systemRoleService.save(systemRole));
    }

    @ApiOperation(value = "根据ID删除系统角色",notes = "根据ID删除系统角色")
    @DeleteMapping(value = "/deleteById")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(systemRoleService.removeById(id));
    }

    @ApiOperation(value = "根据ID更新系统用户",notes = "根据ID更新系统用户")
    @PutMapping(value = "/modifyById")
    public R modifyById(@RequestBody SystemRole systemRole){
        systemRole.setUpdateTime(LocalDateTime.now());
        return R.ok(systemRoleService.updateById(systemRole));
    }

    @ApiOperation(value = "根据ID获取系统角色",notes = "根据ID获取系统角色")
    @GetMapping(value = "/getById")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemRoleService.getById(id));
    }
}
