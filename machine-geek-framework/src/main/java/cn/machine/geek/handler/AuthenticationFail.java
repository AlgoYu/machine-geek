package cn.machine.geek.handler;

import cn.machine.geek.entity.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 认证失败处理器
 * @Date: 2020/10/6
 */
public class AuthenticationFail implements AuthenticationFailureHandler {
    // Jackson
    private ObjectMapper objectMapper;

    public AuthenticationFail(){
        objectMapper = new ObjectMapper();
    }

    /** @Author: MachineGeek
    * @Description: 认证失败处理
    * @Date: 2020/10/6
    * @param httpServletRequest
    * @param httpServletResponse
    * @param e
    * @Return void
    */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(R.fail("用户名或密码不正确"));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
