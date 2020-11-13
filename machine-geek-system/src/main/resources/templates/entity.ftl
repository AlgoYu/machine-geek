package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
/**
* @Author: MachineGeek
* @Description: ${moduleName}类
* @Email: 794763733@qq.com
* @Date: ${date}
*/
@Data
@ApiModel(description = "${moduleName}")
@TableName(value = "${tableName}")
public class ${className}{
<#list data as value>
    <#if value.columnKey == "PRI">
    @TableId(value = "`${value.columnName}`")
    <#else>
    @TableField(value = "`${value.columnName}`")
    </#if>
    @ApiModelProperty(value = "${value.columnComment}")
    <#switch value.dataType>
    <#case "bigint">
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long ${toHump(value.columnName)};
    <#break>
    <#case "int">
    private Integer ${toHump(value.columnName)};
    <#break>
    <#case "datetime">
    private LocalDateTime ${toHump(value.columnName)};
    <#break>
    <#default>
    private String ${toHump(value.columnName)};
    </#switch>

</#list>
}