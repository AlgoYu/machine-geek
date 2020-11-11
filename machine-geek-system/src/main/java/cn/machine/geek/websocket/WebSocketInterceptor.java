package cn.machine.geek.websocket;

import cn.machine.geek.constant.WebConstant;
import cn.machine.geek.entity.LoginUser;
import cn.machine.geek.service.ITokenService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: WebSocket拦截器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/11
 */
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Autowired
    private ITokenService tokenService;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String tokenHeader = httpServletRequest.getHeader(WebConstant.TOKEN_HEADER);
        if (!StringUtil.isNullOrEmpty(tokenHeader)) {
            if (tokenService.existsAccessToken(tokenHeader)) {
                LoginUser loginUser = tokenService.getAccessToken(tokenHeader);
                attributes.put("id",loginUser.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
