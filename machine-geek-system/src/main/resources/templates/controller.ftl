package cn.machine.geek.controller;

import cn.machine.geek.constant.WebConstant;
import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.entity.${className};
import cn.machine.geek.service.I${className}Service;
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
* @Description: ${moduleName}控制器
* @Email: 794763733@qq.com
* @Date: ${date}
*/
@Api(tags = "${moduleName}接口")
@RestController
@RequestMapping(value = "/${instanceName}")
public class ${className}Controller {
    @Autowired
    private I${className}Service ${instanceName}Service;

    @ApiOperation(value = "分页获取${moduleName}",notes = "分页获取${moduleName}")
    @GetMapping(value = "/list")
    public R list(@Validated PageRequest pageRequest){
        return R.ok(${instanceName}Service.page(new Page<>(1,10)));
    }

    @ApiOperation(value = "增加${moduleName}",notes = "增加${moduleName}")
    @PostMapping(value = "/add")
    @Transactional
    public R add(@RequestBody ${className} ${instanceName}){
        ${instanceName}.setCreateTime(LocalDateTime.now());
        return R.ok(${instanceName}Service.save(systemUser));
    }

    @ApiOperation(value = "根据ID删除${moduleName}",notes = "根据ID删除${moduleName}")
    @DeleteMapping(value = "/deleteById")
    public R deleteById(@RequestParam(value = "id") Long id){
        return R.ok(${instanceName}Service.removeById(id));
    }

    @ApiOperation(value = "根据ID更新${moduleName}",notes = "根据ID更新${moduleName}")
    @PutMapping(value = "/modifyById")
    @Transactional
    public R modifyById(@RequestBody ${className} ${instanceName}){
        ${instanceName}.setUpdateTime(LocalDateTime.now());
        return R.ok(${instanceName}Service.updateById(systemUser));
    }

    @ApiOperation(value = "根据ID获取${moduleName}",notes = "根据ID获取${moduleName}")
    @GetMapping(value = "/getById")
    public R getById(@RequestParam(value = "id") Long id){
        return R.ok(${instanceName}Service.getById(id));
    }
}