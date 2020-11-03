package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemAuthority;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 菜单传输类
 * @Date: 2020/10/28
 */
public class AuthorityTree extends SystemAuthority {
    private List<AuthorityTree> children;

    public List<AuthorityTree> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorityTree> children) {
        this.children = children;
    }
}
