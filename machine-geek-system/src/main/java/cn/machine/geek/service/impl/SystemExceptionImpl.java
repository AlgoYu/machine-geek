package cn.machine.geek.service.impl;

import cn.machine.geek.entity.SystemException;
import cn.machine.geek.mapper.ISystemExceptionMapper;
import cn.machine.geek.service.ISystemExceptionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: 系统异常服务类
 * @Date: 2020/11/6
 */
@Service
public class SystemExceptionImpl extends ServiceImpl<ISystemExceptionMapper, SystemException> implements ISystemExceptionService {
    @Override
    public IPage<SystemException> paging(int page, int size, String keyWord) {
        QueryWrapper<SystemException> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(SystemException::getUri,keyWord)
                .or().like(SystemException::getExceptionMessage,keyWord)
                .or().like(SystemException::getExceptionClass,keyWord)
                .or().like(SystemException::getParameter,keyWord);
        return baseMapper.selectPage(new Page<>(page,size),queryWrapper);
    }
}
