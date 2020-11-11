package cn.machine.geek.mapper;

import cn.machine.geek.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: MachineGeek
 * @Description: 系统用户映射类
 * @Date: 2020/10/3
 */
@Mapper
public interface ISystemUserMapper extends BaseMapper<SystemUser> {
}
