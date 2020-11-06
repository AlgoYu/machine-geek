package cn.machine.geek.mapper;

import cn.machine.geek.entity.SystemException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: MachineGeek
 * @Description: 系统异常映射类
 * @Date: 2020/11/6
 */
@Mapper
public interface ISystemExceptionMapper extends BaseMapper<SystemException> {
}
