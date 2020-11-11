package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemAuthority;
import lombok.Data;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 菜单传输类
 * @Date: 2020/10/28
 */
@Data
public class SystemAuthorityTree extends SystemAuthority {
    private List<SystemAuthorityTree> children;
}
