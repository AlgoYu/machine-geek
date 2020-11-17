package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import cn.machine.geek.mapper.ISystemDictionaryMapper;
import cn.machine.geek.service.ISystemDictionaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 系统字典控制器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/17
 */
@Controller
@RequestMapping(value = "/systemDictionary")
public class SystemDictionaryController {
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @ApiOperation(value = "根据KEY获得字典数据",notes = "根据KEY获得字典数据")
    @GetMapping(value = "/getByKey")
    public R getByKey(@RequestParam(value = "key") String key){
        return R.ok(systemDictionaryService.getByKey(key));
    }

    @ApiOperation(value = "增加字典数据",notes = "增加字典数据")
    @PostMapping(value = "/insert")
    @Transactional
    public R insert(@RequestBody Map<String,String> map){
        for (String key : map.keySet()){
            systemDictionaryService.insert(key,map.get(key));
        }
        return R.ok();
    }
}
