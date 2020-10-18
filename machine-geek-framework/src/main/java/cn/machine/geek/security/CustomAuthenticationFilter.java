package cn.machine.geek.security;

import cn.machine.geek.entity.LoginUser;
import cn.machine.geek.entity.R;
import cn.machine.geek.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 自定义验证逻辑类
 * @Date: 2020/10/6
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // Token
    private TokenService tokenService;
    // Jackson
    private ObjectMapper objectMapper;

    public CustomAuthenticationFilter(TokenService tokenService, ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.objectMapper = objectMapper;
    }

    /** @Author: MachineGeek
    * @Description: 重写验证逻辑使用JSON
    * @Date: 2020/10/6
    * @param request
    * @param response
    * @Return org.springframework.security.core.Authentication
    */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 判断是否是JSON
        if(MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())){
            // 构建验证对象
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
            try {
                // 转换为JSON对象
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String temp = null;
                while ((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp);
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                // 赋值验证对象
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jsonObject.getString("username"),jsonObject.getString("password"));
                return usernamePasswordAuthenticationToken;
            } catch (Exception e) {
                e.printStackTrace();
                // 赋值出错则为空
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("","");
            }finally {
                // 提交验证对象
                this.setDetails(request,usernamePasswordAuthenticationToken);
                return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
            }
        }
        // 不是JSON调用父类FORM表单
        return super.attemptAuthentication(request, response);
    }

    /**
    * @Author: MachineGeek
    * @Description: 验证成功的执行方法
    * @Date: 2020/10/10 11:33
    * @param request:
    * @param response:
    * @param chain:
    * @param authResult:
    * @return: void
    */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 构建Token
        String accessToken = tokenService.createAccessToken((LoginUser) authResult.getPrincipal());
        String refreshToken = tokenService.createRefreshToken(null);
        // 构建需要返回给前端的数据
        Map<String,Object> data = new HashMap<>();
        data.put("accessToken",accessToken);
        data.put("refreshToken",refreshToken);
        data.put("user",authResult.getPrincipal());
        // 返回JSON
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String json = objectMapper.writeValueAsString(R.ok(data));
        writer.print(json);
        writer.flush();
        writer.close();
    }

    /**
    * @Author: MachineGeek
    * @Description: 验证失败的执行方法
    * @Date: 2020/10/10 11:34
    * @param request:
     * @param response:
     * @param failed:
    * @return: void
    */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String json = objectMapper.writeValueAsString(R.fail("用户名或密码不正确"));
        writer.print(json);
        writer.flush();
        writer.close();
    }
}
