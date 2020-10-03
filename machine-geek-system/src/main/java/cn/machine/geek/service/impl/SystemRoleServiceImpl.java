package cn.machine.geek.service.impl;

import cn.machine.geek.entity.SystemRole;
import cn.machine.geek.mapper.ISystemRoleMapper;
import cn.machine.geek.service.ISystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统角色实现类
 * @Date: 2020/10/3
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<ISystemRoleMapper, SystemRole> implements ISystemRoleService {
    @Override
    public List<SystemRole> listByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }
}
