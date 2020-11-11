package cn.machine.geek.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: MachineGeek
 * @Description: WebSocket连接池
 * @Email: 794763733@qq.com
 * @Date: 2020/11/11
 */
public class WebSocketSessionManager {
    // WebSocket连接池
    private static ConcurrentHashMap<String, WebSocketSession> WebSocketSessionPool = new ConcurrentHashMap<String, WebSocketSession>();

    /**
    * @Author: MachineGeek
    * @Description: 存入WebSocketSession
    * @Date: 2020/11/11
     * @param key
     * @param webSocketSession
    * @Return: void
    */
    public static void putSession(String key,WebSocketSession webSocketSession){
        WebSocketSessionPool.put(key,webSocketSession);
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取WebSocketSession
    * @Date: 2020/11/11
     * @param key
    * @Return: javax.websocket.Session
    */
    public static WebSocketSession getSession(String key){
        return WebSocketSessionPool.get(key);
    }

    /**
    * @Author: MachineGeek
    * @Description: 删除WebSocketSession
    * @Date: 2020/11/11
     * @param key
    * @Return: void
    */
    public static void deleteSession(String key){
        WebSocketSession webSocketSession = WebSocketSessionPool.remove(key);
        if(webSocketSession!=null){
            try {
                webSocketSession.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    * @Author: MachineGeek
    * @Description: 发送给WebSocketSession文本数据
    * @Date: 2020/11/11
     * @param key
     * @param data
    * @Return: void
    */
    public static void sendText(String key,TextMessage data){
        try {
            if("ALL".equals(key)){
                for (Map.Entry<String,WebSocketSession> entry : WebSocketSessionPool.entrySet()){
                    entry.getValue().sendMessage(data);
                }
            }else{
                WebSocketSessionPool.get(key).sendMessage(data);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}