package cn.machine.geek.controller;

import cn.machine.geek.constant.WebConstant;
import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.entity.SystemUser;
import cn.machine.geek.service.ISystemUserService;
import cn.machine.geek.service.ITokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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
    private ITokenService tokenService;

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
    @DeleteMapping(value = "/deleteById")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(systemUserService.removeById(id));
    }

    @ApiOperation(value = "根据ID更新系统用户",notes = "根据ID更新系统用户")
    @PutMapping(value = "/modifyById")
    public R modifyById(@RequestBody SystemUser systemUser){
        systemUser.setUpdateTime(LocalDateTime.now());
        return R.ok(systemUserService.updateById(systemUser));
    }

    @ApiOperation(value = "根据ID获取系统用户",notes = "根据ID获取系统用户")
    @GetMapping(value = "/getById")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(systemUserService.getById(id));
    }

    @ApiOperation(value = "获取当前登录用户",notes = "获取当前登录用户")
    @GetMapping(value = "/getCurrent")
    public R getCurrent(HttpServletRequest request){
        String tokenStr = request.getHeader(WebConstant.TOKEN_HEADER);
        return this.getById(tokenService.getAccessToken(tokenStr).getId());
    }
}
