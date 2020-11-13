package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统用户类
 * @Date: 2020/10/3
 */
@ApiModel(value = "系统用户")
@Data
public class SystemUser{
    @ApiModelProperty(value = "ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "系统用户名称")
    @TableField(value = "`username`")
    private String username;

    @ApiModelProperty(value = "系统用户密码")
    @TableField(value = "`password`")
    private String password;

    @ApiModelProperty(value = "系统用户图片")
    @TableField(value = "`picture`")
    private String picture;

    @ApiModelProperty(value = "系统用户昵称")
    @TableField(value = "`nickname`")
    private String nickname;

    @ApiModelProperty(value = "系统用户描述")
    @TableField(value = "`description`")
    private String description;

    @ApiModelProperty(value = "系统用户邮箱")
    @TableField(value = "`email`")
    private String email;

    @ApiModelProperty(value = "系统用户手机号")
    @TableField(value = "`phone`")
    private String phone;

    @ApiModelProperty(value = "系统用户IP")
    @TableField(value = "`ip`")
    private String ip;

    @ApiModelProperty(value = "系统用户禁用")
    @TableField(value = "`disable`")
    private Boolean disable;

    @ApiModelProperty(value = "乐观锁")
    @TableField(value = "`version`")
    private Integer version;

    @ApiModelProperty(value = "系统用户上一次登录时间")
    @TableField(value = "`last_login`")
    private LocalDateTime lastLogin;

    @ApiModelProperty(value = "系统用户创建时间")
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "系统用户修改时间")
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}
