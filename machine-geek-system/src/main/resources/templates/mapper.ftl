package cn.machine.geek.mapper;

import cn.machine.geek.entity.${className};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @Author: MachineGeek
* @Description: ${moduleName}映射类
* @Email: 794763733@qq.com
* @Date: ${date}
*/
@Mapper
public interface I${className}Mapper extends BaseMapper<${className}> {
}