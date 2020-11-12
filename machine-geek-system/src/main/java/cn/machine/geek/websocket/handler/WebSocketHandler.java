package cn.machine.geek.websocket.handler;

import cn.machine.geek.websocket.entity.WebSocketMessage;
import cn.machine.geek.websocket.service.IWebSocketPublishService;
import cn.machine.geek.websocket.utils.WebSocketSessionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Author: MachineGeek
 * @Description: WebSocket、Redis订阅处理器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/11
 */
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler implements MessageListener {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IWebSocketPublishService webSocketPublishService;
    /**
    * @Author: MachineGeek
    * @Description: WebSocket连接成功
    * @Date: 2020/11/11
     * @param session
    * @Return: void
    */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        WebSocketSessionManager.putSession(session.getAttributes().get("id").toString(),session);
    }

    /**
    * @Author: MachineGeek
    * @Description: 接受客户端消息
    * @Date: 2020/11/11
     * @param session
     * @param message
    * @Return: void
    */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        webSocketPublishService.publish(message.getPayload());
    }

    /**
    * @Author: MachineGeek
    * @Description: WebSocket连接关闭
    * @Date: 2020/11/11
     * @param session
     * @param status
    * @Return: void
    */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        WebSocketSessionManager.deleteSession(session.getAttributes().get("id").toString());
    }

    /**
    * @Author: MachineGeek
    * @Description: 传输出错
    * @Date: 2020/11/11
     * @param session
     * @param exception
    * @Return: void
    */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        WebSocketSessionManager.deleteSession(session.getAttributes().get("id").toString());
    }

    /**
    * @Author: MachineGeek
    * @Description: 订阅Redis发来的消息
    * @Date: 2020/11/12
     * @param message
     * @param bytes
    * @Return: void
    */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        try {
            WebSocketMessage webSocketMessage = objectMapper.readValue(message.toString(), WebSocketMessage.class);
            WebSocketSessionManager.sendText(webSocketMessage.getId(),new TextMessage(message.getBody()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
