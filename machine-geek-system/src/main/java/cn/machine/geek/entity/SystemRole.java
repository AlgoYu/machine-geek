package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统角色类
 * @Date: 2020/10/3
 */
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
