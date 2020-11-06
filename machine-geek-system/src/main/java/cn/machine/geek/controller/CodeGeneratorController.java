package cn.machine.geek.controller;

import cn.machine.geek.dto.PageRequest;
import cn.machine.geek.dto.R;
import cn.machine.geek.service.IDatabaseService;
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
 * @Description: 代码生成器控制器
 * @Date: 2020/11/6
 */
@Api(tags = "代码生成器接口")
@RestController
@RequestMapping(value = "/codeGenerator")
public class CodeGeneratorController {
    @Autowired
    private IDatabaseService databaseService;

    @ApiOperation(value = "获取数据库表",notes = "获取数据库表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('DEVELOP:GENERATOR:GET')")
    public R list(@Validated PageRequest pageRequest){
        return R.ok(databaseService.getTableByCondition(pageRequest.getPage(),pageRequest.getSize(),pageRequest.getKeyWord()));
    }
}
