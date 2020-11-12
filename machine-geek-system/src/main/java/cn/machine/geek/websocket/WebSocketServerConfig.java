package cn.machine.geek.websocket;

import org.springframework.beans.factory.annotation.Autowired;
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
    private final String WEBSOCKET_PATH = "websocket";
    /**
    * @Author: MachineGeek
    * @Description: 注册WebSocket
    * @Date: 2020/11/12
     * @param registry
    * @Return: void
    */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler,WEBSOCKET_PATH)
                .addInterceptors(webSocketInterceptor)
                .setAllowedOrigins("*");
    }
}
