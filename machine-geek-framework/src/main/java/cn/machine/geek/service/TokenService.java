package cn.machine.geek.service;

/**
 * @Author: MachineGeek
 * @Description: Token服务类
 * @Date: 2020/10/9 15:43
 */
public interface TokenService {
    boolean existsToken(String key);
    String createAccessToken(String key, Object info);
    String createRefreshToken(String key, Object info);
    Object getAccessToken(String key);
    Object getRefreshToken(String key);
    void deleteToken(String key);
}