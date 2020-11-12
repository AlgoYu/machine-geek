package cn.machine.geek.websocket.constant;

import org.springframework.data.redis.listener.ChannelTopic;

/**
 * @Author: MachineGeek
 * @Description: WebSocket常量
 * @Email: 794763733@qq.com
 * @Date: 2020/11/12
 */
public class WebSocketConstant {
    public static final String URI = "websocket";
    public static final String TOKEN = "token";
    public static ChannelTopic WEB_SOCKET_CHANNEL_TOPIC = new ChannelTopic("WebSocket");
}
