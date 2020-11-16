package cn.machine.geek.entity;

import cn.machine.geek.enums.AuthorityEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统权力类
 * @Date: 2020/10/3
 */
@ApiModel(value = "系统权限")
@Data
public class SystemAuthority implements GrantedAuthority {
    @ApiModelProperty(value = "ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "系统权限名称")
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(value = "系统权限字符")
    @TableField(value = "`key`")
    private String key;

    @ApiModelProperty(value = "系统权限描述")
    @TableField(value = "`description`")
    private String description;

    @ApiModelProperty(value = "系统权限类型")
    @TableField(value = "`type`")
    private AuthorityEnum type;

    @ApiModelProperty(value = "系统权限路径")
    @TableField(value = "`path`")
    private String path;

    @ApiModelProperty(value = "系统权限父级")
    @TableField(value = "`parent_id`")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    @ApiModelProperty(value = "系统权限排序")
    @TableField(value = "`sort`")
    private Integer sort;

    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "`version`")
    private Integer version;

    @ApiModelProperty(value = "系统权限创建时间")
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "系统权限修改时间")
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;

    @Override
    public String getAuthority() {
        return this.key;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SystemAuthority) {
            return this.key.equals(((SystemAuthority) obj).key);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    @Override
    public String toString() {
        return this.key;
    }
}
