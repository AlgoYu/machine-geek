package cn.machine.geek.dto;

import cn.machine.geek.enums.AuthorityEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 菜单传输类
 * @Date: 2020/10/28
 */
public class AuthorityTree {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String name;
    private String key;
    private Integer sort;
    private String description;
    private AuthorityEnum type;
    private String path;
    private List<AuthorityTree> children;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorityEnum getType() {
        return type;
    }

    public void setType(AuthorityEnum type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<AuthorityTree> getChildren() {
        return children;
    }

    public void setChildren(List<AuthorityTree> children) {
        this.children = children;
    }
}
