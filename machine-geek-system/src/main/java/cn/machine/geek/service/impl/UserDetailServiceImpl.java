package cn.machine.geek.service.impl;

import cn.machine.geek.entity.*;
import cn.machine.geek.enums.AuthorityEnum;
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
        // 根据用户名查询出用户
        SystemUser systemUser = systemUserService.getByUserName(username);
        if(null == systemUser){
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 根据用户名查询出角色与权限
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<SystemRole> systemRoles = systemRoleService.listByUserId(systemUser.getId());
        List<SystemAuthority> systemAuthorities = systemAuthorityService.listByUserId(systemUser.getId());
        // 增加角色信息到权限集合中
        systemRoles.forEach((systemRole)->{
            SystemAuthority systemAuthority = new SystemAuthority();
            systemAuthority.setType(AuthorityEnum.ROLE);
            systemAuthority.setKey(systemRole.getKey());
            authorities.add(systemAuthority);
        });
        // 添加权限信息到权限集合中
        authorities.addAll(systemAuthorities);
        // 返回登录对象
        return new LoginUser(systemUser.getId(),systemUser.getUsername(),systemUser.getPassword(),!systemUser.getDisable(),authorities,true,true,true);
    }
}
