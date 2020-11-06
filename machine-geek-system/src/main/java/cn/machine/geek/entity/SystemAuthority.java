package cn.machine.geek.entity;

import cn.machine.geek.enums.AuthorityEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统权力类
 * @Date: 2020/10/3
 */
@Data
public class SystemAuthority implements GrantedAuthority {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @TableField(value = "`name`")
    private String name;
    @TableField(value = "`key`")
    private String key;
    @TableField(value = "`description`")
    private String description;
    @TableField(value = "`type`")
    private AuthorityEnum type;
    @TableField(value = "`path`")
    private String path;
    @TableField(value = "`parent_id`")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;
    @TableField(value = "`sort`")
    private Integer sort;
    @TableField(value = "`disable`")
    private Boolean disable;
    @TableField(value = "`version`")
    private Integer version;
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;
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
