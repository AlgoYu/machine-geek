package cn.machine.geek.service;

import cn.machine.geek.entity.SystemUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统用户服务类
 * @Date: 2020/10/3
 */
public interface ISystemUserService extends IService<SystemUser> {
    IPage<SystemUser> listByCondition(int page,int size,String keyWord);
    SystemUser getByUserName(String username);
}
