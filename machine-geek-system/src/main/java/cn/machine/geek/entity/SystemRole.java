package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统角色类
 * @Date: 2020/10/3
 */
@ApiModel(value = "系统角色")
@Data
public class SystemRole {
    @ApiModelProperty(value = "ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "系统角色名称")
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty(value = "系统角色字符")
    @TableField(value = "`key`")
    private String key;

    @ApiModelProperty(value = "系统角色描述")
    @TableField(value = "`description`")
    private String description;

    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "`version`")
    private Integer version;

    @ApiModelProperty(value = "系统角色创建时间")
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "系统角色更新时间")
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}
