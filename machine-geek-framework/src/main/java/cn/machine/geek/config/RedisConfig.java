package cn.machine.geek.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: MachineGeek
 * @Description: Token配置类
 * @Date: 2020/10/6
 */
@Configuration
public class RedisConfig {

    public static long accessTokenExpire;
    public static long refreshTokenExpire;

    @Value(value = "${token.accessTokenExpire}")
    public void setAccessTokenExpire(long accessTokenExpire) {
        RedisConfig.accessTokenExpire = accessTokenExpire;
    }

    @Value(value = "${token.refreshTokenExpire}")
    public void setRefreshTokenExpire(long refreshTokenExpire) {
        RedisConfig.refreshTokenExpire = refreshTokenExpire;
    }
}
