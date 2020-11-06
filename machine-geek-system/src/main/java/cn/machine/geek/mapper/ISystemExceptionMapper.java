package cn.machine.geek.mapper;

import cn.machine.geek.entity.SystemException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: MachineGeek
 * @Description: 系统异常映射类
 * @Date: 2020/11/6
 */
@Mapper
public interface ISystemExceptionMapper extends BaseMapper<SystemException> {
    IPage<SystemException> selectByCondition(IPage<SystemException> page, @Param(value = "keyWord") String keyWord);
}
