package cn.machine.geek.security;

import cn.machine.geek.entity.R;
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
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(R.ok(authentication.getPrincipal()));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
