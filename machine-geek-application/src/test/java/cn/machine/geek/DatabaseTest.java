package cn.machine.geek;

import cn.machine.geek.service.impl.CodeGeneratorImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: MachineGeek
 * @Description: 数据库查询测试
 * @Date: 2020/11/6
 */
@Log4j2
@SpringBootTest
public class DatabaseTest {
    @Autowired
    private CodeGeneratorImpl codeGenerator;

    @Test
    public void test(){
        codeGenerator.generate("system_user","用户");
    }
}
