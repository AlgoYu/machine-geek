package cn.machine.geek.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author: MachineGeek
 * @Description: 权限类型枚举
 * @Date: 2020/10/24
 */
public enum  AuthorityEnum {
    ROLE(100),MODULE(0),MENU(1),API(2);

    @EnumValue
    private Integer type;

    AuthorityEnum(Integer type) {
        this.type = type;
    }

    @JsonCreator
    public static AuthorityEnum getItem(int code){
        for(AuthorityEnum item : values()){
            if(item.getType() == code){
                return item;
            }
        }
        return null;
    }

    @JsonValue
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
