package cn.machine.geek.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @Author: MachineGeek
 * @Description: 分页请求
 * @Date: 2020/10/19
 */
@ApiModel(value = "分页请求")
@Data
public class PageRequest {
    @ApiModelProperty(value = "页号")
    @Min(value = 1,message = "当前页不能小于1")
    private int page;
    @ApiModelProperty(value = "页尺寸")
    @Min(value = 1,message = "页尺寸不能小于1")
    private int size;
    @ApiModelProperty(value = "关键字")
    private String keyWord;
}
