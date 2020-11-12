package cn.machine.geek.websocket.config;

import cn.machine.geek.websocket.constant.WebSocketConstant;
import cn.machine.geek.websocket.handler.WebSocketHandler;
import cn.machine.geek.websocket.interceptor.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Author: MachineGeek
 * @Description: WebSocket配置
 * @Email: 794763733@qq.com
 * @Date: 2020/11/12
 */
@Component
@EnableWebSocket
public class WebSocketServerConfig implements WebSocketConfigurer {
    @Autowired
    private WebSocketHandler webSocketHandler;
    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    /**
    * @Author: MachineGeek
    * @Description: 注册WebSocket
    * @Date: 2020/11/12
     * @param registry
    * @Return: void
    */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, WebSocketConstant.URI)
                .addInterceptors(webSocketInterceptor)
                .setAllowedOrigins("*");
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册消息监听适配器
    * @Date: 2020/11/12
     * @param
    * @Return: org.springframework.data.redis.listener.adapter.MessageListenerAdapter
    */
    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        return new MessageListenerAdapter(webSocketHandler);
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册适配器监听的频道
    * @Date: 2020/11/12
     * @param redisConnectionFactory
    * @Return: org.springframework.data.redis.listener.RedisMessageListenerContainer
    */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter,WebSocketConstant.WEB_SOCKET_CHANNEL_TOPIC);
        return redisMessageListenerContainer;
    }
}
