package cn.machine.geek;

import cn.machine.geek.service.ICodeGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/10
 */
@SpringBootTest
public class CodeGeneratorTest {
    @Autowired
    private ICodeGeneratorService codeGeneratorService;

    @Test
    public void generateTest(){
        codeGeneratorService.generate("system_user","系统用户");
    }
}
