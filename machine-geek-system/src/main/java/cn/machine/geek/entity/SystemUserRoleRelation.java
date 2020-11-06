package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 用户角色关系类
 * @Date: 2020/11/3
 */
@Data
public class SystemUserRoleRelation {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`user_id`")
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`role_id`")
    private Long roleId;
}
