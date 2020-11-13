package cn.machine.geek.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: MachineGeek
 * @Description: 登录用户类
 * @Date: 2020/10/18
 */
@ApiModel(value = "登录信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {
    @ApiModelProperty(value = "登录ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @ApiModelProperty(value = "登录用户名")
    private String username;
    @ApiModelProperty(value = "登录密码")
    private String password;
    @ApiModelProperty(value = "是否启用")
    private boolean enable;
    @ApiModelProperty(value = "权限")
    private Collection<GrantedAuthority> authorities;
    @ApiModelProperty(value = "凭证过期")
    private boolean credentialsNonExpired;
    @ApiModelProperty(value = "账户过期")
    private boolean accountNonExpired;
    @ApiModelProperty(value = "账户锁定")
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
        return this.enable;
    }
}
