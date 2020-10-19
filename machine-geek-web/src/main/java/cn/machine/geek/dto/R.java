package cn.machine.geek.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: MachineGeek
 * @Description: Response响应结果类
 * @Date: 2020/10/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R {
    private boolean success;
    private int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    /** @Author: MachineGeek
     * @Description: 成功
     * @Date: 2020/10/6
     * @param
     * @Return cn.machine.geek.entity.R
     */
    public static R ok(){
        return new R(true,200,null,null);
    }

    /** @Author: MachineGeek
     * @Description: 成功携带数据
     * @Date: 2020/10/6
     * @param
     * @Return cn.machine.geek.entity.R
     */
    public static R ok(Object data){
        return new R(true,200,null,data);
    }

    /** @Author: MachineGeek
     * @Description: 失败
     * @Date: 2020/10/6
     * @param
     * @Return cn.machine.geek.entity.R
     */
    public static R fail(String msg){
        return new R(false,200,msg,null);
    }
}
