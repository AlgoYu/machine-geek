package cn.machine.geek.service;

import cn.machine.geek.entity.SystemException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: MachineGeek
 * @Description: 系统异常服务
 * @Date: 2020/11/6
 */
public interface ISystemExceptionService extends IService<SystemException> {
    IPage<SystemException> paging(int page, int size, String keyWord);
}
