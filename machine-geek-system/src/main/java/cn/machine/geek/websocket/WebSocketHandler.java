package cn.machine.geek.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/11
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private ObjectMapper objectMapper;
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
        WebSocketMessage webSocketMessage = objectMapper.readValue(message.getPayload(), WebSocketMessage.class);
        WebSocketSessionManager.sendText(webSocketMessage.getId(),message);
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
}
