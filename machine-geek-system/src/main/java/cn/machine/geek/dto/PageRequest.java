package cn.machine.geek.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: MachineGeek
 * @Description: 分页请求
 * @Date: 2020/10/19
 */
public class PageRequest {
    @Min(value = 1,message = "当前页不能小于1")
    private int page;
    @Min(value = 1,message = "页尺寸不能小于1")
    private int size;
    @NotNull(message = "关键字不能为空")
    private String keyWord;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
