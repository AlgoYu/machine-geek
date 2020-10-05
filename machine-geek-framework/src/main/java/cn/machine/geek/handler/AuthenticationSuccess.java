package cn.machine.geek.handler;

import cn.machine.geek.entity.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 认证成功处理器
 * @Date: 2020/10/6
 */
public class AuthenticationSuccess implements AuthenticationSuccessHandler {
    // Jackson
    private ObjectMapper objectMapper;

    public AuthenticationSuccess(){
        objectMapper = new ObjectMapper();
    }

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
