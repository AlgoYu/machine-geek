package cn.machine.geek.entity;

import cn.machine.geek.enums.AuthorityEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统权力类
 * @Date: 2020/10/3
 */
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorityEnum getType() {
        return this.type;
    }

    public void setType(AuthorityEnum type) {
        this.type = type;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDisable() {
        return this.disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

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
