package cn.machine.geek.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 数据库表列类
 * @Date: 2020/11/9
 */
@ApiModel(value = "数据库列")
@Data
public class DatabaseTableColumn {
    @ApiModelProperty(value = "数据库表列名")
    private String columnName;
    @ApiModelProperty(value = "数据库表列数据类型")
    private String dataType;
    @ApiModelProperty(value = "数据库表列注释")
    private String columnComment;
    @ApiModelProperty(value = "数据库表列主键")
    private String columnKey;
}
