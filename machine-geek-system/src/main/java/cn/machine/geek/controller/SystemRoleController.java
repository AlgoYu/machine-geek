package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.dto.SystemRoleDTO;
import cn.machine.geek.entity.SystemAuthority;
import cn.machine.geek.entity.SystemRole;
import cn.machine.geek.entity.SystemRoleAuthorityRelation;
import cn.machine.geek.service.ISystemAuthorityService;
import cn.machine.geek.service.ISystemRoleAuthorityRelationService;
import cn.machine.geek.service.ISystemRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ISystemAuthorityService systemAuthorityService;
    @Autowired
    private ISystemRoleAuthorityRelationService systemRoleAuthorityRelationService;

    @ApiOperation(value = "分页获取系统角色",notes = "分页获取系统角色")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:GET')")
    public R list(@Validated PageRequest pageRequest){
        return R.ok(systemRoleService.listByCondition(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }

    @ApiOperation(value = "增加系统角色",notes = "增加系统角色")
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:ADD')")
    @Transactional
    public R add(@RequestBody SystemRole systemRole){
        systemRole.setCreateTime(LocalDateTime.now());
        return R.ok(systemRoleService.save(systemRole));
    }

    @ApiOperation(value = "根据ID删除系统角色",notes = "根据ID删除系统角色")
    @DeleteMapping(value = "/deleteById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:DELETE')")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(systemRoleService.removeById(id));
    }

    @ApiOperation(value = "根据ID更新系统用户",notes = "根据ID更新系统用户")
    @PutMapping(value = "/modifyById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:MODIFY')")
    @Transactional
    public R modifyById(@RequestBody SystemRole systemRole){
        systemRole.setUpdateTime(LocalDateTime.now());
        return R.ok(systemRoleService.updateById(systemRole));
    }

    @ApiOperation(value = "根据ID更新系统用户及权限",notes = "根据ID更新系统用户及权限")
    @PutMapping(value = "/modifyWithAuthorityById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:MODIFY')")
    @Transactional
    public R modifyWithAuthorityById(@RequestBody SystemRoleDTO systemRoleDTO){
        // 清除角色与权力之间的关系
        QueryWrapper<SystemRoleAuthorityRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SystemRoleAuthorityRelation::getRoleId,systemRoleDTO.getId());
        systemRoleAuthorityRelationService.remove(queryWrapper);
        // 重新添加角色与权力的关系
        List<SystemRoleAuthorityRelation> systemRoleAuthorityRelations = new ArrayList<>();
        systemRoleDTO.getAuthorityIds().forEach((id)->{
            SystemRoleAuthorityRelation systemRoleAuthorityRelation = new SystemRoleAuthorityRelation();
            systemRoleAuthorityRelation.setRoleId(systemRoleDTO.getId());
            systemRoleAuthorityRelation.setAuthorityId(id);
            systemRoleAuthorityRelations.add(systemRoleAuthorityRelation);
        });
        systemRoleAuthorityRelationService.saveBatch(systemRoleAuthorityRelations);
        // 更新角色信息
        systemRoleDTO.setUpdateTime(LocalDateTime.now());
        return R.ok(systemRoleService.updateById(systemRoleDTO));
    }

    @ApiOperation(value = "根据ID获取系统角色",notes = "根据ID获取系统角色")
    @GetMapping(value = "/getById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:GET')")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemRoleService.getById(id));
    }

    @ApiOperation(value = "根据ID获取系统角色与权限",notes = "根据ID获取系统角色与权限")
    @GetMapping(value = "/getWithAuthorityById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMROLE:GET')")
    public R getWithAuthorityById(@RequestParam(value = "id") Long id){
        SystemRoleDTO systemRoleDTO = new SystemRoleDTO();
        BeanUtils.copyProperties(systemRoleService.getById(id), systemRoleDTO);
        systemRoleDTO.setAuthorityIds(new ArrayList<>());
        List<SystemAuthority> systemAuthorities = systemAuthorityService.listByRoleId(systemRoleDTO.getId());
        systemAuthorities.forEach((systemAuthority) -> {
            systemRoleDTO.getAuthorityIds().add(systemAuthority.getId());
        });
        return R.ok(systemRoleDTO);
    }
}
