package cn.machine.geek.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: MachineGeek
 * @Description: 用户权限类
 * @Date: 2020/10/18
 */
public class Authority implements GrantedAuthority {
    private String authority;

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
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

        if (obj instanceof Authority) {
            return authority.equals(((Authority) obj).authority);
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
