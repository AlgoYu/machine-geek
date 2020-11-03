package cn.machine.geek.service.impl;

import cn.machine.geek.entity.SystemUserRoleRelation;
import cn.machine.geek.mapper.ISystemUserRoleRelationMapper;
import cn.machine.geek.service.ISystemUserRoleRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: 用户角色关系服务实现类
 * @Date: 2020/11/3
 */
@Service
public class SystemUserRoleRelationServiceImpl extends ServiceImpl<ISystemUserRoleRelationMapper, SystemUserRoleRelation> implements ISystemUserRoleRelationService {
}
