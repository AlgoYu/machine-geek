package cn.machine.geek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @Author: MachineGeek
* @Description: 安全配置类
* @Date: 2020/10/3
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /** @Author: MachineGeek
     * @Description: 配置认证路径
     * @Date: 2020/10/3
     * @param http
     * @Return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 不认证路径
        http.authorizeRequests()
                .antMatchers("/static/**","/doc.html","/api-docs-ext/**","/swagger-resources/**","/api-docs/**","/swagger-ui.html","/swagger-resources/configuration/ui/**","/swagger-resources/configuration/security/**")
                .permitAll();
        // 表单登录注销
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        // 其余地址全部认证
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    /** @Author: MachineGeek
    * @Description: 注册密码加密器
    * @Date: 2020/10/4
     * @param
    * @Return org.springframework.security.crypto.password.PasswordEncoder
    */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
