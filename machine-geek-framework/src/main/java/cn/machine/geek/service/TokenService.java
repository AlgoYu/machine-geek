package cn.machine.geek.service;

import cn.machine.geek.entity.LoginUser;

/**
 * @Author: MachineGeek
 * @Description: Token服务类
 * @Date: 2020/10/9 15:43
 */
public interface TokenService {
    boolean existsAccessToken(String key);
    boolean existsRefreshToken(String key);
    String createAccessToken(LoginUser info);
    String createRefreshToken(LoginUser info);
    LoginUser getAccessToken(String key);
    LoginUser getRefreshToken(String key);
    void deleteAccessToken(String key);
    void deleteRefreshToken(String key);
}