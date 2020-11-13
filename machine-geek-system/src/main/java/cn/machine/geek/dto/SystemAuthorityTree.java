package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemAuthority;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 菜单传输类
 * @Date: 2020/10/28
 */
@Data
public class SystemAuthorityTree extends SystemAuthority {
    @ApiModelProperty(value = "子级系统权限")
    private List<SystemAuthorityTree> children;
}
