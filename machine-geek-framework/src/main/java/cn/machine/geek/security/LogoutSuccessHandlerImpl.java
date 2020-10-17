package cn.machine.geek.security;

import cn.machine.geek.config.RedisConfig;
import cn.machine.geek.config.WebConfig;
import cn.machine.geek.entity.R;
import cn.machine.geek.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 注销处理类
 * @Date: 2020/10/6
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    // Jackson
    @Autowired
    private ObjectMapper objectMapper;
    // Token服务
    @Autowired
    private TokenService tokenService;
    /** @Author: MachineGeek
    * @Description: 用户注销处理
    * @Date: 2020/10/17
    * @param httpServletRequest
    * @param httpServletResponse
    * @param authentication
    * @Return void
    */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        tokenService.deleteAccessToken(httpServletRequest.getParameter("id"));
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(R.ok());
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
