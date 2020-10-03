package cn.machine.geek.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统角色类
 * @Date: 2020/10/3
 */
@Data
public class SystemRole {
    private Long id;
    private String name;
    private String key;
    private String description;
    private Boolean disable;
    private Integer version;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
