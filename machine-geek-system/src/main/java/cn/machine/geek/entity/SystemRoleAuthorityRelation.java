package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author: MachineGeek
 * @Description: 角色权力关系类
 * @Date: 2020/11/3
 */
public class SystemRoleAuthorityRelation {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`role_id`")
    private Long roleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`authority_id`")
    private Long authorityId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}
