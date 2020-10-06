package cn.machine.geek.security;

import cn.machine.geek.config.RedisConfig;
import cn.machine.geek.entity.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
    // Redis
    @Autowired
    private RedisTemplate redisTemplate;

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
        // 使用UUID生成Token
        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        // 存储Token进Redis
        redisTemplate.opsForHash().put(RedisConfig.accessTokenPrefix + accessToken,RedisConfig.PRINCIPAL_KEY,authentication.getPrincipal());
        redisTemplate.opsForHash().put(RedisConfig.accessTokenPrefix + accessToken,RedisConfig.AUTHORITIES_KEY,authentication.getAuthorities());
        redisTemplate.opsForValue().set(RedisConfig.refreshTokenPrefix + refreshToken,authentication.getName(),RedisConfig.refreshTokenExpire,TimeUnit.SECONDS);
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
