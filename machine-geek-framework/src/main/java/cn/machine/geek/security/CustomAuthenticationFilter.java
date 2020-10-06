package cn.machine.geek.security;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: MachineGeek
 * @Description: 自定义验证逻辑类
 * @Date: 2020/10/6
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
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
}
