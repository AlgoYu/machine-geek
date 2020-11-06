package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统角色类
 * @Date: 2020/10/3
 */
@Data
public class SystemRole {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @TableField(value = "`name`")
    private String name;
    @TableField(value = "`key`")
    private String key;
    @TableField(value = "`description`")
    private String description;
    @TableField(value = "`disable`")
    private Boolean disable;
    @TableField(value = "`version`")
    private Integer version;
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}
