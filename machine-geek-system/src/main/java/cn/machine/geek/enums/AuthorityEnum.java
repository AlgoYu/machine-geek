package cn.machine.geek.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @Author: MachineGeek
 * @Description: 权限类型枚举
 * @Date: 2020/10/24
 */
public enum  AuthorityEnum {
    MODULE(0),MENU(1),API(2);

    @EnumValue
    private Integer type;

    AuthorityEnum(Integer type) {
        this.type = type;
    }
}
