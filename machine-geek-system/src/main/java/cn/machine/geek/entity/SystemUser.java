package cn.machine.geek.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统用户类
 * @Date: 2020/10/3
 */
@Data
public class SystemUser{
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String description;
    private String email;
    private String phone;
    private String ip;
    private Boolean disable;
    private Integer version;
    private LocalDateTime lastLogin;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
