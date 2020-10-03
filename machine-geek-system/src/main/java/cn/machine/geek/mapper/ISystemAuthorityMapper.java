package cn.machine.geek.mapper;

import cn.machine.geek.entity.SystemAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统权力映射类
 * @Date: 2020/10/3
 */
@Mapper
public interface ISystemAuthorityMapper extends BaseMapper<SystemAuthority> {
    List<SystemAuthority> selectByUserId(@Param(value = "userId")Long userId);
}
