package cn.machine.geek.service.impl;

import cn.machine.geek.mapper.ISystemDictionaryMapper;
import cn.machine.geek.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/17
 */
@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
    @Autowired
    private ISystemDictionaryMapper systemDictionaryMapper;

    @Override
    public String getByKey(String key) {
        return systemDictionaryMapper.selectByKey(key);
    }

    @Override
    public Map<String, String> list() {
        return systemDictionaryMapper.list();
    }

    @Override
    public Integer insert(String key, String value) {
        return systemDictionaryMapper.insert(key,value);
    }
}
