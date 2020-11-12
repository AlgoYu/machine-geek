package cn.machine.geek.websocket.service;

/**
 * @Author: MachineGeek
 * @Description: Redis订阅发布服务
 * @Email: 794763733@qq.com
 * @Date: 2020/11/12
 */
public interface IWebSocketPublishService {
    void publish(String content);
}
