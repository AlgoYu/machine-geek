package cn.machine.geek.security;

import cn.machine.geek.dto.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 未登陆和Token过期处理类
 * @Date: 2020/10/6
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    // Jackson
    @Autowired
    private ObjectMapper objectMapper;

    /** @Author: MachineGeek
    * @Description: 身份过期或未登陆处理
    * @Date: 2020/10/6
    * @param httpServletRequest
    * @param httpServletResponse
    * @param e
    * @Return void
    */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(R.fail("身份过期或未登陆"));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
