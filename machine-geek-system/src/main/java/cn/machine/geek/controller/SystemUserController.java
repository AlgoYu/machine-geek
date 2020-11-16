package cn.machine.geek.controller;

import cn.machine.geek.constant.WebConstant;
import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.dto.SystemUserDTO;
import cn.machine.geek.entity.SystemRole;
import cn.machine.geek.entity.SystemUser;
import cn.machine.geek.entity.SystemUserRoleRelation;
import cn.machine.geek.service.ISystemRoleService;
import cn.machine.geek.service.ISystemUserRoleRelationService;
import cn.machine.geek.service.ISystemUserService;
import cn.machine.geek.service.ITokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统用户控制器
 * @Date: 2020/10/19
 */
@Api(tags = "系统用户接口")
@RestController
@RequestMapping(value = "/systemUser")
public class SystemUserController {
    @Autowired
    private ISystemUserService systemUserService;
    @Autowired
    private ISystemUserRoleRelationService systemUserRoleRelationService;
    @Autowired
    private ISystemRoleService systemRoleService;
    @Autowired
    private ITokenService tokenService;

    @ApiOperation(value = "分页获取系统用户",notes = "分页获取系统用户")
    @GetMapping(value = "/paging")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:GET')")
    public R paging(@Validated PageRequest pageRequest){
        return R.ok(systemUserService.paging(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }

    @ApiOperation(value = "增加系统用户",notes = "增加系统用户")
    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:ADD')")
    @Transactional
    public R add(@RequestBody SystemUser systemUser){
        systemUser.setCreateTime(LocalDateTime.now());
        return R.ok(systemUserService.save(systemUser));
    }

    @ApiOperation(value = "增加系统用户及与角色的关系",notes = "增加系统用户及与角色的关系")
    @PostMapping(value = "/addWithRole")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:ADD')")
    @Transactional
    public R addWithRole(@RequestBody SystemUserDTO systemUserDTO){
        systemUserDTO.setCreateTime(LocalDateTime.now());
        systemUserService.save(systemUserDTO);
        this.addRelations(systemUserDTO);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除系统用户",notes = "根据ID删除系统用户")
    @DeleteMapping(value = "/deleteById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:DELETE')")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(systemUserService.removeById(id));
    }

    @ApiOperation(value = "根据ID更新系统用户",notes = "根据ID更新系统用户")
    @PutMapping(value = "/modifyById")
    @Transactional
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:MODIFY')")
    public R modifyById(@RequestBody SystemUser systemUser){
        systemUser.setUpdateTime(LocalDateTime.now());
        return R.ok(systemUserService.updateById(systemUser));
    }

    @ApiOperation(value = "根据ID更新系统用户和角色",notes = "根据ID更新系统用户和角色")
    @PutMapping(value = "/modifyWithRoleById")
    @Transactional
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:MODIFY')")
    public R modifyWithRoleById(@RequestBody SystemUserDTO systemUserDTO){
        // 删除与用户与角色的关系
        QueryWrapper<SystemUserRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SystemUserRoleRelation::getUserId,systemUserDTO.getId());
        systemUserRoleRelationService.remove(queryWrapper);
        this.addRelations(systemUserDTO);
        // 修改用户信息
        systemUserDTO.setUpdateTime(LocalDateTime.now());
        return R.ok(systemUserService.updateById(systemUserDTO));
    }

    @ApiOperation(value = "根据ID获取系统用户",notes = "根据ID获取系统用户")
    @GetMapping(value = "/getById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:GET')")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemUserService.getById(id));
    }

    @ApiOperation(value = "根据ID获取系统用户",notes = "根据ID获取系统用户")
    @GetMapping(value = "/getWithRoleById")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:GET')")
    public R getWithRoleById(@RequestParam(value = "id") Long id){
        SystemUserDTO systemUserDTO = new SystemUserDTO();
        BeanUtils.copyProperties(systemUserService.getById(id),systemUserDTO);
        List<SystemRole> systemRoles = systemRoleService.listByUserId(systemUserDTO.getId());
        systemUserDTO.setSystemRoleIds(new ArrayList<>());
        systemRoles.forEach((role)->{
            systemUserDTO.getSystemRoleIds().add(role.getId());
        });
        return R.ok(systemUserDTO);
    }

    @ApiOperation(value = "获取登录信息",notes = "获取登录信息")
    @GetMapping(value = "/getLoginInfo")
    @PreAuthorize("hasAuthority('MANAGEMENT:SYSTEMUSER:GET')")
    public R getLoginInfo(HttpServletRequest request){
        String tokenStr = request.getHeader(WebConstant.TOKEN_HEADER);
        return this.getById(tokenService.getAccessToken(tokenStr).getId());
    }

    /**
    * @Author: MachineGeek
    * @Description: 添加系统用户和系统角色的关系
    * @Date: 2020/11/16
     * @param systemUserDTO
    * @Return: java.util.List<cn.machine.geek.entity.SystemUserRoleRelation>
    */
    private void addRelations(SystemUserDTO systemUserDTO){
        // 重新添加用户与角色的关系
        List<SystemUserRoleRelation> systemUserRoleRelations = new ArrayList<>();
        systemUserDTO.getSystemRoleIds().forEach((id)->{
            SystemUserRoleRelation systemUserRoleRelation = new SystemUserRoleRelation();
            systemUserRoleRelation.setUserId(systemUserDTO.getId());
            systemUserRoleRelation.setRoleId(id);
            systemUserRoleRelations.add(systemUserRoleRelation);
        });
        systemUserRoleRelationService.saveBatch(systemUserRoleRelations);
    }
}
