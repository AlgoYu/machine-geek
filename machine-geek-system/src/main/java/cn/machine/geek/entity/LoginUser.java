package cn.machine.geek.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: MachineGeek
 * @Description: 登录用户类
 * @Date: 2020/10/18
 */
public class LoginUser implements UserDetails {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String username;
    private String password;
    private boolean enable;
    private Collection<GrantedAuthority> authorities;
    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private boolean accountNonLocked;

    public LoginUser() {
    }

    public LoginUser(Long id, String username, String password, boolean enable, Collection<GrantedAuthority> authorities, boolean credentialsNonExpired, boolean accountNonExpired, boolean accountNonLocked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.authorities = authorities;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnable() {
        return this.enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

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
        return this.enable;
    }
}
