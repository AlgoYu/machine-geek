package cn.machine.geek.websocket.service.impl;

import cn.machine.geek.websocket.constant.WebSocketConstant;
import cn.machine.geek.websocket.service.IWebSocketPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: Redis订阅发布实现类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/12
 */
@Service
public class WebSocketPublishServiceImpl implements IWebSocketPublishService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
    * @Author: MachineGeek
    * @Description: 向Redis频道发布信息
    * @Date: 2020/11/12
     * @param content
    * @Return: void
    */
    @Override
    public void publish(String content) {
        redisTemplate.convertAndSend(WebSocketConstant.WEB_SOCKET_CHANNEL_TOPIC.getTopic(),content);
    }
}
