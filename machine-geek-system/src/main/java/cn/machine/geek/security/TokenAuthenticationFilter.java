package cn.machine.geek.security;

import cn.machine.geek.constant.WebConstant;
import cn.machine.geek.entity.LoginUser;
import cn.machine.geek.service.ITokenService;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
 * @Description: Token拦截器
 * @Date: 2020/10/17
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    // Token服务
    @Autowired
    private ITokenService tokenService;

    /**
     * @param request
     * @param response
     * @param filterChain
     * @Author: MachineGeek
     * @Description: 拦截并从Redis获取Token数据
     * @Date: 2020/10/17
     * @Return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader(WebConstant.TOKEN_HEADER);
        if (!StringUtil.isNullOrEmpty(tokenHeader)) {
            if (tokenService.existsAccessToken(tokenHeader)) {
                LoginUser loginUser = tokenService.getAccessToken(tokenHeader);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
