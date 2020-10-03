package cn.machine.geek.config;

import cn.machine.geek.entity.SystemUser;
import cn.machine.geek.service.ISystemAuthorityService;
import cn.machine.geek.service.ISystemRoleService;
import cn.machine.geek.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author: MachineGeek
 * @Description: 用户细节实现类
 * @Date: 2020/10/3
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private ISystemUserService ISystemUserService;
    @Autowired
    private ISystemRoleService ISystemRoleService;
    @Autowired
    private ISystemAuthorityService ISystemAuthorityService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SystemUser systemUser = ISystemUserService.getByName(s);
        if(null == systemUser){
            throw new UsernameNotFoundException("用户不存在！");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return null;
    }
}
