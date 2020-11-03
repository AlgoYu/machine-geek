package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author: MachineGeek
 * @Description: 用户角色关系类
 * @Date: 2020/11/3
 */
public class SystemUserRoleRelation {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`user_id`")
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`role_id`")
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
