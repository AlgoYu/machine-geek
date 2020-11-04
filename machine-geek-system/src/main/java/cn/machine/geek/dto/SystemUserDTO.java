package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemUser;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public class SystemUserDTO extends SystemUser {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> systemRoleIds;

    public List<Long> getSystemRoleIds() {
        return systemRoleIds;
    }

    public void setSystemRoleIds(List<Long> systemRoleIds) {
        this.systemRoleIds = systemRoleIds;
    }
}
