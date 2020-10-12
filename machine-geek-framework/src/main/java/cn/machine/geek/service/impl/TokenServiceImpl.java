package cn.machine.geek.service.impl;

import cn.machine.geek.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MachineGeek
 * @Description: Token实现类
 * @Date: 2020/10/9 15:46
 */
@Service
public class TokenServiceImpl implements TokenService {
    public final static String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_";
    public final static String REFRESH_TOKEN_KEY = "REFRESH_TOKEN_";
    // AccessToken 过期时间
    @Value(value = "${token.accessTokenExpire}")
    private long accessTokenExpire;
    // RefreshToken 过期时间
    @Value(value = "${token.refreshTokenExpire}")
    private long refreshTokenExpire;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
    * @Author: MachineGeek
    * @Description: 是否存在访问Token
    * @Date: 2020/10/9 16:36
    * @param key:
    * @return: boolean
    */
    @Override
    public boolean existsAccessToken(String key) {
        return redisTemplate.hasKey(this.ACCESS_TOKEN_KEY + key);
    }

    /**
    * @Author: MachineGeek
    * @Description: 是否存在刷新Token
    * @Date: 2020/10/9 16:37
    * @param key:
    * @return: boolean
    */
    @Override
    public boolean existsRefreshToken(String key) {
        return redisTemplate.hasKey(this.REFRESH_TOKEN_KEY + key);
    }

    /**
    * @Author: MachineGeek
    * @Description: 创建访问Token
    * @Date: 2020/10/9 16:35
    * @param key:
     * @param info:
    * @return: java.lang.String
    */
    @Override
    public String createAccessToken(String key, Object info) {
        String accessToken = UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(this.ACCESS_TOKEN_KEY + key,info,accessTokenExpire, TimeUnit.SECONDS);
        return accessToken;
    }

    /**
    * @Author: MachineGeek
    * @Description: 创建刷新Token
    * @Date: 2020/10/9 16:35
    * @param key:
     * @param info:
    * @return: java.lang.String
    */
    @Override
    public String createRefreshToken(String key, Object info) {
        String refreshToken = UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(this.REFRESH_TOKEN_KEY + key,info,this.refreshTokenExpire,TimeUnit.SECONDS);
        return refreshToken;
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取访问Token
    * @Date: 2020/10/9 16:33
    * @param key:
    * @return: java.lang.Object
    */
    @Override
    public Object getAccessToken(String key) {
        return this.redisTemplate.opsForValue().get(this.ACCESS_TOKEN_KEY + key);
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取刷新Token
    * @Date: 2020/10/9 16:33
    * @param key:
    * @return: java.lang.Object
    */
    @Override
    public Object getRefreshToken(String key) {
        return this.redisTemplate.opsForValue().get(this.REFRESH_TOKEN_KEY + key);
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除Token
    * @Date: 2020/10/9 16:33
    * @param key:
    * @return: void
    */
    @Override
    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }
}
