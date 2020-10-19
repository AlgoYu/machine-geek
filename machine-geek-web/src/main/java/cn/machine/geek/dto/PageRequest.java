package cn.machine.geek.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: MachineGeek
 * @Description: 分页请求
 * @Date: 2020/10/19
 */
@Data
public class PageRequest {
    @Min(value = 1,message = "当前页不能小于1")
    private int page;
    @Min(value = 1,message = "页尺寸不能小于1")
    private int size;
    @NotNull(message = "关键字不能为空")
    private String keyWord;
}
