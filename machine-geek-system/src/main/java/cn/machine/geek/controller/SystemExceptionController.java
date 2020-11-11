package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.service.ISystemExceptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/paging")
    @PreAuthorize("hasAuthority('DEVELOP:SYSTEMEXCEPTION:GET')")
    public R paging(@Validated PageRequest pageRequest){
        return R.ok(systemExceptionService.paging(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }

    @ApiOperation(value = "清空异常信息",notes = "清空异常信息")
    @DeleteMapping(value = "/deleteById")
    @PreAuthorize("hasAuthority('DEVELOP:SYSTEMEXCEPTION:DELETE')")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(systemExceptionService.removeById(id));
    }

    @ApiOperation(value = "清空异常信息",notes = "清空异常信息")
    @DeleteMapping(value = "/clear")
    @PreAuthorize("hasAuthority('DEVELOP:SYSTEMEXCEPTION:CLEAR')")
    public R clear(){
        return R.ok(systemExceptionService.remove(new QueryWrapper<>()));
    }
}
