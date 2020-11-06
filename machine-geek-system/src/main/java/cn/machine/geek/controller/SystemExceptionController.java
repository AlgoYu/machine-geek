package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.service.ISystemExceptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 系统异常控制器
 * @Date: 2020/11/6
 */
@Api(tags = "系统异常接口")
@RestController
@RequestMapping(value = "/systemException")
public class SystemExceptionController {
    @Autowired
    private ISystemExceptionService systemExceptionService;

    @ApiOperation(value = "分页获取系统异常",notes = "分页获取系统异常")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('DEVELOP:SYSTEMEXCEPTION:GET')")
    public R list(@Validated PageRequest pageRequest){
        return R.ok(systemExceptionService.listByCondition(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }
}
