package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
* @Author: MachineGeek
* @Description: ${moduleName}类
* @Email: 794763733@qq.com
* @Date: ${date}
*/
@Data
@TableName(value = "${tableName}")
public class ${tableName}{
<#list data as value>
    <#if value.columnKey == "PRI">
    @TableId(value = "`${value.columnName}`")
    <#else>
    @TableField(value = "`${value.columnName}`")
    </#if>
    <#switch value.dataType>
    <#case "bigint">
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long ${value.columnName};
    <#break>
    <#case "int">
    private Integer ${value.columnName};
    <#break>
    <#case "datetime">
    private LocalDateTime ${value.columnName};
    <#break>
    <#default>
    private String ${value.columnName};
    </#switch>
</#list>
}