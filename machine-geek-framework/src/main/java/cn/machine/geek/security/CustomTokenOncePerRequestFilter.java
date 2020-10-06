package cn.machine.geek.security;

import cn.machine.geek.config.RedisConfig;
import cn.machine.geek.config.WebConfig;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: MachineGeek
 * @Description: 自定义Token认证过滤器
 * @Date: 2020/10/6
 */
@Component
public class CustomTokenOncePerRequestFilter extends OncePerRequestFilter {
    // Redis
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(WebConfig.TOKEN_HEADER);
        if(!StringUtil.isNullOrEmpty(token)){
            String accessTokenKey = RedisConfig.accessTokenPrefix + token;
            if(redisTemplate.hasKey(accessTokenKey) && redisTemplate.getExpire(accessTokenKey) > 0){
                //UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken()
            }
        }
    }
}
