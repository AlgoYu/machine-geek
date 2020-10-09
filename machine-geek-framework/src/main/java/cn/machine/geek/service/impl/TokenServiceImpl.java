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
    public final static String ACCESS_TOKEN_PREFIX = "ACCESS_TOKEN_";
    public final static String REFRESH_TOKEN_PREFIX = "REFRESH_TOKEN_";
    // AccessToken 过期时间
    @Value(value = "${token.accessTokenExpire}")
    private long accessTokenExpire;
    // RefreshToken 过期时间
    @Value(value = "${token.refreshTokenExpire}")
    private long refreshTokenExpire;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean existsToken(String key) {
        return this.redisTemplate.hasKey(key);
    }

    @Override
    public String createAccessToken(String key, Object info) {
        String accessToken = UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(this.ACCESS_TOKEN_PREFIX + key,info,accessTokenExpire, TimeUnit.SECONDS);
        return accessToken;
    }

    @Override
    public String createRefreshToken(String key, Object info) {
        String refreshToken = UUID.randomUUID().toString();
        this.redisTemplate.opsForValue().set(this.REFRESH_TOKEN_PREFIX + key,info);
        return refreshToken;
    }

    @Override
    public Object getAccessToken(String key) {
        return this.redisTemplate.opsForValue().get(this.ACCESS_TOKEN_PREFIX + key);
    }

    @Override
    public Object getRefreshToken(String key) {
        return this.redisTemplate.opsForValue().get(this.REFRESH_TOKEN_PREFIX + key);
    }

    @Override
    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }
}
