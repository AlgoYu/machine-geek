package cn.machine.geek.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @Author: MachineGeek
 * @Description: 用户权限类
 * @Date: 2020/10/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthority implements GrantedAuthority {
    private String authority;
    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SimpleGrantedAuthority) {
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
