package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 角色权力关系类
 * @Date: 2020/11/3
 */
@Data
public class SystemRoleAuthorityRelation {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`role_id`")
    private Long roleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableField(value = "`authority_id`")
    private Long authorityId;
}
