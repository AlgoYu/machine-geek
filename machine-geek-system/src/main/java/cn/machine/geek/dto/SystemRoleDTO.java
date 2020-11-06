package cn.machine.geek.dto;

import cn.machine.geek.entity.SystemRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 系统角色传输对象
 * @Date: 2020/11/3
 */
@Data
public class SystemRoleDTO extends SystemRole {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> systemAuthorityIds;
}
