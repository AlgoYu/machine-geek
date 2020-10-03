package cn.machine.geek.service.impl;

import cn.machine.geek.entity.SystemAuthority;
import cn.machine.geek.mapper.ISystemAuthorityMapper;
import cn.machine.geek.service.ISystemAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: 系统权力实现类
 * @Date: 2020/10/3
 */
@Service
public class SystemAuthorityServiceImpl extends ServiceImpl<ISystemAuthorityMapper, SystemAuthority> implements ISystemAuthorityService {
}
