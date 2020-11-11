package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import cn.machine.geek.entity.SystemAuthority;
import cn.machine.geek.enums.AuthorityEnum;
import cn.machine.geek.service.ISystemAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统权限控制器
 * @Date: 2020/10/28
 */
@Api(tags = "系统权限接口")
@RestController
@RequestMapping(value = "/systemAuthority")
public class SystemAuthorityController {
    @Autowired
    private ISystemAuthorityService systemAuthorityService;

    @ApiOperation(value = "获取权限树",notes = "获取权限树")
    @GetMapping(value = "/tree")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMAUTHORITY:GET')")
    public R tree(){
        // 转换为菜单树返回
        return R.ok(systemAuthorityService.tree());
    }

    @ApiOperation(value = "增加系统权限",notes = "增加权限")
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMAUTHORITY:ADD')")
    @Transactional
    public R add(@RequestBody SystemAuthority systemAuthority){
        systemAuthority.setSort(0);
        systemAuthority.setType(AuthorityEnum.API);
        systemAuthority.setDisable(false);
        systemAuthority.setVersion(0);
        systemAuthority.setCreateTime(LocalDateTime.now());
        return R.ok(systemAuthorityService.save(systemAuthority));
    }

    @ApiOperation(value = "根据ID修改系统权限",notes = "根据ID修改系统权限")
    @GetMapping(value = "/modifyById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMAUTHORITY:MODIFY')")
    @Transactional
    public R modifyById(@RequestBody SystemAuthority systemAuthority){
        systemAuthority.setUpdateTime(LocalDateTime.now());
        return R.ok(systemAuthorityService.updateById(systemAuthority));
    }

    @ApiOperation(value = "根据ID获取系统权限",notes = "根据ID获取系统权限")
    @GetMapping(value = "/getById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMAUTHORITY:GET')")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemAuthorityService.getById(id));
    }
}
