package cn.machine.geek.security;

import cn.machine.geek.entity.R;
import cn.machine.geek.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 认证成功处理类
 * @Date: 2020/10/6
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    // Jackson
    @Autowired
    private ObjectMapper objectMapper;
    // Token
    @Autowired
    private TokenService tokenService;

    /** @Author: MachineGeek
    * @Description: 认证成功处理
    * @Date: 2020/10/6
    * @param httpServletRequest
    * @param httpServletResponse
    * @param authentication
    * @Return void
    */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 构建Token
        String accessToken = tokenService.createAccessToken(authentication.getName(),authentication.getPrincipal());
        String refreshToken = tokenService.createAccessToken(authentication.getName(),null);
        // 构建需要返回给前端的数据
        Map<String,Object> data = new HashMap<>();
        data.put("accessToken",accessToken);
        data.put("refreshToken",refreshToken);
        data.put("user",authentication.getPrincipal());
        // 返回JSON
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(R.ok(data));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
