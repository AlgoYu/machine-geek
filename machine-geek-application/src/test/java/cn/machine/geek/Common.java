package cn.machine.geek;

import cn.machine.geek.service.IEmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/13
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Common {
    @Autowired
    private IEmailService emailService;

    @Test
    public void email(){
        emailService.sendEmail("794763733@qq.com","邮箱测试","臭前端","2592972482@qq.com");
    }
}
