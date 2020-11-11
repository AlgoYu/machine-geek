package cn.machine.geek.service.impl;

import cn.machine.geek.entity.SystemRole;
import cn.machine.geek.mapper.ISystemRoleMapper;
import cn.machine.geek.service.ISystemRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<SystemRole> paging(int page, int size, String keyWord) {
        QueryWrapper<SystemRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(SystemRole::getKey,keyWord)
                .or().like(SystemRole::getName,keyWord)
                .or().like(SystemRole::getDescription,keyWord);
        return baseMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
}