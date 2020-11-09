package cn.machine.geek.entity;

import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 数据库表列类
 * @Date: 2020/11/9
 */
@Data
public class DatabaseTableColumn {
    private String columnName;
    private String dataType;
    private String columnComment;
    private String columnKey;
}
