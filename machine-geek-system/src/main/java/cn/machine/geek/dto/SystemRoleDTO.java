package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemRole;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统角色传输对象
 * @Date: 2020/11/3
 */
public class SystemRoleDTO extends SystemRole {
    private List<Long> authorityIds;

    public List<Long> getAuthorityIds() {
        return authorityIds;
    }

    public void setAuthorityIds(List<Long> authorityIds) {
        this.authorityIds = authorityIds;
    }
}
