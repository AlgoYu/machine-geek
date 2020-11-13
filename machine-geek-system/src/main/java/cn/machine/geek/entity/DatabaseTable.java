package cn.machine.geek.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 数据库表类
 * @Date: 2020/11/6
 */
@ApiModel(value = "数据库表")
@Data
public class DatabaseTable {
    @ApiModelProperty(value = "数据库表名")
    private String tableName;
    @ApiModelProperty(value = "数据库表存储引擎")
    private String engine;
    @ApiModelProperty(value = "数据库表注释")
    private String tableComment;
    @ApiModelProperty(value = "数据库表创建时间")
    private String createTime;
}
