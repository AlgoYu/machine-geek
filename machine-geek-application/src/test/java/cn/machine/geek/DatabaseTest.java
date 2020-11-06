package cn.machine.geek;

import cn.machine.geek.mapper.IDatabaseMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 数据库查询测试
 * @Date: 2020/11/6
 */
@Log4j2
@SpringBootTest
public class DatabaseTest {
    @Autowired
    private IDatabaseMapper databaseMapper;

    @Test
    public void test(){
        List<String> strings = databaseMapper.selectAllDatabaseName();
        log.info(strings.toString());
        List<String> machine_geek = databaseMapper.selectAllTableByDatabaseName("machine_geek");
        log.info(machine_geek.toString());
    }
}
