package cn.machine.geek.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统权力类
 * @Date: 2020/10/3
 */
@Data
public class SystemAuthority {
    private Long id;
    private String name;
    private String key;
    private String description;
    private Integer sort;
    private Boolean disable;
    private Integer version;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
