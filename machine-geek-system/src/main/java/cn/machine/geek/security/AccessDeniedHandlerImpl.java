package cn.machine.geek.security;

import cn.machine.geek.dto.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: MachineGeek
 * @Description: 访问拒绝处理类
 * @Date: 2020/10/6
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    // Jackson
    @Autowired
    private ObjectMapper objectMapper;

    /** @Author: MachineGeek
    * @Description: 访问拒绝处理
    * @Date: 2020/10/6
    * @param httpServletRequest
    * @param httpServletResponse
    * @param accessDeniedException
    * @Return void
    */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        String json = objectMapper.writeValueAsString(R.fail("无权访问"));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
