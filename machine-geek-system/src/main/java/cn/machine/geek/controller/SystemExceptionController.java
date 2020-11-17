package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.entity.SystemException;
import cn.machine.geek.service.ISystemExceptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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
        QueryWrapper<SystemException> queryWrapper = new QueryWrapper<>();
        String keyWord = pageRequest.getKeyWord();
        if (!StringUtils.isEmpty(keyWord)){
            queryWrapper.lambda().like(SystemException::getUri,keyWord)
                    .or().like(SystemException::getExceptionMessage,keyWord)
                    .or().like(SystemException::getExceptionClass,keyWord)
                    .or().like(SystemException::getParameter,keyWord);
        }
        return R.ok(systemExceptionService.page(new Page<>(pageRequest.getPage(),pageRequest.getSize()),queryWrapper));
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
