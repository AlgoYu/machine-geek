package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemUser;

import java.util.List;

public class SystemUserDTO extends SystemUser {
    private List<Long> systemRoles;

    public List<Long> getSystemRoles() {
        return systemRoles;
    }

    public void setSystemRoles(List<Long> systemRoles) {
        this.systemRoles = systemRoles;
    }
}
