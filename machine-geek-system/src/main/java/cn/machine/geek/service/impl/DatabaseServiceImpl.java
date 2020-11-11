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
    public List<String> listDatabase() {
        return databaseMapper.listDatabase();
    }

    @Override
    public IPage<DatabaseTable> pagingTableByDatabaseName(int page, int size, String keyWord) {
        return databaseMapper.pagingTableByDatabaseName(new Page<>(page,size),databaseName,keyWord);
    }
}
