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
        if(MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String temp = null;
                while ((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp);
                }
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jsonObject.getString("username"),jsonObject.getString("password"));
                return usernamePasswordAuthenticationToken;
            } catch (Exception e) {
                e.printStackTrace();
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("","");
            }finally {
                this.setDetails(request,usernamePasswordAuthenticationToken);
                return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
            }
        }
        return super.attemptAuthentication(request, response);
    }
}
