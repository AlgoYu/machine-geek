package cn.machine.geek.websocket.entity;

import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: WebSocket消息体
 * @Email: 794763733@qq.com
 * @Date: 2020/11/11
 */
@Data
public class WebSocketMessage {
    private String id;
    private Object data;
}
