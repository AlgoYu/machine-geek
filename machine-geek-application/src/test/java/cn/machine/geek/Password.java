package cn.machine.geek;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: MachineGeek
 * @Description: 启动类
 * @Date: 2020/10/5
 */
public class Password {

    public static void main(String[] args) {
        System.out.print(new BCryptPasswordEncoder().encode("123456"));
    }
}
