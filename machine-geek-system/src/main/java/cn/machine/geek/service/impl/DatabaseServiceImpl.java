package cn.machine.geek.service.impl;

import cn.machine.geek.entity.DatabaseTable;
import cn.machine.geek.mapper.IDatabaseMapper;
import cn.machine.geek.service.IDatabaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 数据库服务实现类
 * @Date: 2020/11/6
 */
@Service
public class DatabaseServiceImpl implements IDatabaseService {
    @Autowired
    private IDatabaseMapper databaseMapper;
    @Value("${mysql.database}")
    private String databaseName;
    @Override
    public List<String> getAllDatabaseName() {
        return databaseMapper.selectAllDatabaseName();
    }

    @Override
    public IPage<DatabaseTable> getTableByCondition(int page, int size, String keyWord) {
        return databaseMapper.selectTableByCondition(new Page<>(page,size),databaseName,keyWord);
    }
}
