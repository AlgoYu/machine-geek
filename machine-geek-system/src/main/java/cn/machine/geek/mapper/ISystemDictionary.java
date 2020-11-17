package cn.machine.geek.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 系统字典映射类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/17
 */
@Mapper
public interface ISystemDictionary {
    Map<String,String> list();
    Integer insert(@Param(value = "key")String key,@Param(value = "value")String value);
}
