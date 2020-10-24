package cn.machine.geek.service;

import cn.machine.geek.entity.SystemRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统角色服务类
 * @Date: 2020/10/3
 */
public interface ISystemRoleService extends IService<SystemRole> {
    List<SystemRole> listByUserId(Long userId);
    IPage<SystemRole> listByCondition(int page, int size, String keyWord);
}
