package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 系统异常类
 * @Date: 2020/11/6
 */
@ApiModel(value = "系统异常")
@Data
public class SystemException {
    @ApiModelProperty(value = "ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "资源定位符")
    @TableField(value = "`uri`")
    private String uri;

    @ApiModelProperty(value = "请求方法")
    @TableField(value = "`method`")
    private String method;

    @ApiModelProperty(value = "请求参数")
    @TableField(value = "`parameter`")
    private String parameter;

    @ApiModelProperty(value = "IP地址")
    @TableField(value = "`ip`")
    private String ip;

    @ApiModelProperty(value = "异常类")
    @TableField(value = "`exception_class`")
    private String exceptionClass;

    @ApiModelProperty(value = "异常信息")
    @TableField(value = "`exception_message`")
    private String exceptionMessage;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`")
    private LocalDateTime createTime;
}
