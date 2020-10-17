package cn.machine.geek.service;

/**
 * @Author: MachineGeek
 * @Description: Token服务类
 * @Date: 2020/10/9 15:43
 */
public interface TokenService {
    boolean existsAccessToken(String key);
    boolean existsRefreshToken(String key);
    String createAccessToken(Object info);
    String createRefreshToken(Object info);
    Object getAccessToken(String key);
    Object getRefreshToken(String key);
    void deleteAccessToken(String key);
    void deleteRefreshToken(String key);
}