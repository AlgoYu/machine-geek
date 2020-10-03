package cn.machine.geek.mapper;

import cn.machine.geek.entity.SystemRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统角色映射类
 * @Date: 2020/10/3
 */
public interface ISystemRoleMapper extends BaseMapper<SystemRole> {
    List<SystemRole> selectByUserId(@Param(value = "userId") Long userId);
}
