package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统用户类
 * @Date: 2020/10/3
 */
@Data
public class SystemUser{
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    @TableField(value = "`username`")
    private String username;
    @TableField(value = "`password`")
    private String password;
    @TableField(value = "`picture`")
    private String picture;
    @TableField(value = "`nickname`")
    private String nickname;
    @TableField(value = "`description`")
    private String description;
    @TableField(value = "`email`")
    private String email;
    @TableField(value = "`phone`")
    private String phone;
    @TableField(value = "`ip`")
    private String ip;
    @TableField(value = "`disable`")
    private Boolean disable;
    @TableField(value = "`version`")
    private Integer version;
    @TableField(value = "`last_login`")
    private LocalDateTime lastLogin;
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;
    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}
