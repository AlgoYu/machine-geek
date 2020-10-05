package cn.machine.geek.service;

import cn.machine.geek.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: MachineGeek
 * @Description: 系统用户服务类
 * @Date: 2020/10/3
 */
public interface ISystemUserService extends IService<SystemUser> {
    SystemUser getByUserName(String username);
}
