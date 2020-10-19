package cn.machine.geek.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: MachineGeek
 * @Description: 用户权限类
 * @Date: 2020/10/18
 */
public class UserAuthority implements GrantedAuthority {
    private String authority;

    public UserAuthority() {
    }

    public UserAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof UserAuthority) {
            return authority.equals(((UserAuthority) obj).authority);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }
}
