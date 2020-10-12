package cn.machine.geek.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: MachineGeek
 * @Description: Jackson配置类
 * @Date: 2020/10/10 11:35
 */
@Configuration
public class JacksonConfig {
    /** @Author: MachineGeek
     * @Description: 注册Jackson序列化类
     * @Date: 2020/10/6
     * @param
     * @Return com.fasterxml.jackson.databind.ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
