package cn.machine.geek.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: MachineGeek
 * @Description: 登录用户类
 * @Date: 2020/10/18
 */
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private String username;
    private String password;
    private boolean enabled;
    private Collection<GrantedAuthority> authorities;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private boolean accountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
