package cn.machine.geek.mapper;

import cn.machine.geek.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: MachineGeek
 * @Description: 系统用户映射类
 * @Date: 2020/10/3
 */
@Mapper
public interface ISystemUserMapper extends BaseMapper<SystemUser> {
    IPage<SystemUser> selectByCondition(IPage<SystemUser> page,@Param(value = "keyWord") String keyWord);
}
