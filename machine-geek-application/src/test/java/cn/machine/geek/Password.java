package cn.machine.geek;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: MachineGeek
 * @Description:
 * @Date: 2020/10/5
 */
@Slf4j
public class Password {

    public static void main(String[] args) {
        log.info(new BCryptPasswordEncoder().encode("123456"));
    }
}
