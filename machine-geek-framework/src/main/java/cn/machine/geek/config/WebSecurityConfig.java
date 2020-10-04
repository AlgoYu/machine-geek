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
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 忽略路径
    private String[] ignores = new String[]{"/upload/**","/static/**","/doc.html","/api-docs-ext/**","/swagger-resources/**","/api-docs/**","/swagger-ui.html","/swagger-resources/configuration/ui/**","/swagger-resources/configuration/security/**"};

    /** @Author: MachineGeek
     * @Description: 配置认证路径
     * @Date: 2020/10/3
     * @param http
     * @Return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭跨域和CSRF攻击
        http.cors().disable().csrf().disable();
        // 不需要认证的资源
        http.authorizeRequests()
                .antMatchers(this.ignores)
                .permitAll();
        // 表单登录注销
        http.formLogin()
                .loginPage("/login.ftlh")
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
