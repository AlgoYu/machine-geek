package cn.machine.geek.service;
<#assign className = toHump(tableName)?cap_first>
import cn.machine.geek.entity.${className};
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @Author: MachineGeek
* @Description: ${moduleName}服务类
* @Email: 794763733@qq.com
* @Date: ${date}
*/
public interface I${moduleName}Service extends IService<${className}> {
}