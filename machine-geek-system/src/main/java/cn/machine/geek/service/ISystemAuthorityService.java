package cn.machine.geek.service;

import cn.machine.geek.entity.SystemAuthority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统权力服务类
 * @Date: 2020/10/3
 */
public interface ISystemAuthorityService extends IService<SystemAuthority> {
    List<SystemAuthority> listByUserId(Long userId);
}
