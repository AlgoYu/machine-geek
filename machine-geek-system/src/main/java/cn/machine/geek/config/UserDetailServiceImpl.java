package cn.machine.geek.config;

import cn.machine.geek.entity.SystemAuthority;
import cn.machine.geek.entity.SystemRole;
import cn.machine.geek.entity.SystemUser;
import cn.machine.geek.service.ISystemAuthorityService;
import cn.machine.geek.service.ISystemRoleService;
import cn.machine.geek.service.ISystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 用户细节实现类
 * @Date: 2020/10/3
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private ISystemUserService systemUserService;
    @Autowired
    private ISystemRoleService systemRoleService;
    @Autowired
    private ISystemAuthorityService systemAuthorityService;

    /** @Author: MachineGeek
    * @Description: 通过用户名加载用户
    * @Date: 2020/10/5
    * @param username
    * @Return org.springframework.security.core.userdetails.UserDetails
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserService.getByUserName(username);
        if(null == systemUser){
            throw new UsernameNotFoundException("用户不存在！");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<SystemRole> systemRoles = systemRoleService.listByUserId(systemUser.getId());
        List<SystemAuthority> systemAuthorities = systemAuthorityService.listByUserId(systemUser.getId());

        systemRoles.forEach((role)->{
            authorities.add(new SimpleGrantedAuthority(role.getKey()));
        });
        systemAuthorities.forEach((authority)->{
            authorities.add(new SimpleGrantedAuthority(authority.getKey()));
        });

        return new User(systemUser.getUsername(),systemUser.getPassword(),!systemUser.getDisable(),true,true,true,authorities);
    }
}
