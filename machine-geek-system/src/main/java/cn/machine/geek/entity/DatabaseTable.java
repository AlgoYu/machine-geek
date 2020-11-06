package cn.machine.geek.entity;

import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 数据库表类
 * @Date: 2020/11/6
 */
@Data
public class DatabaseTable {
    private String tableName;
    private String engine;
    private String tableComment;
    private String createTime;
}
