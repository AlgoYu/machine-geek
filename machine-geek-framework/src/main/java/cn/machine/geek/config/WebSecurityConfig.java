package cn.machine.geek.config;

import cn.machine.geek.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
* @Author: MachineGeek
* @Description: 安全配置类
* @Date: 2020/10/3
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,jsr250Enabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 自定义未登陆逻辑
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    // 自定义认证成功逻辑
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    // 自定义认证失败逻辑
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    // 自定义访问拒绝逻辑
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    // 自定义注销逻辑
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    // 静态资源忽略路径
    private String[] ignores = new String[]{"/upload/**","/static/**","/doc.html","/webjars/**","/v2/**","/api-docs-ext","/swagger-resources/**","/api-docs","/swagger-ui.html"};

    /** @Author: MachineGeek
    * @Description: 静态资源配置
    * @Date: 2020/10/5
    * @param web
    * @Return void
    */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(this.ignores);
    }

    /** @Author: MachineGeek
     * @Description: 配置认证路径
     * @Date: 2020/10/3
     * @param http
     * @Return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置表单登录
        http
                // 关闭Session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置注销路径
                .logout()
                .logoutUrl("/logout")
                // 设置注销处理逻辑
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and()
                // 设置其余请求全部拦截
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                // 设置未登陆与访问拒绝逻辑
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .accessDeniedHandler(this.accessDeniedHandler)
                .and()
                // 设置关闭CSRF与CORS
                .cors().disable()
                .csrf().disable();

        // 设置自定义登录逻辑
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();
        // 设置自定义的登陆成功失败逻辑
        customAuthenticationFilter.setAuthenticationSuccessHandler(this.authenticationSuccessHandler);
        customAuthenticationFilter.setAuthenticationFailureHandler(this.authenticationFailureHandler);
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        try {
            customAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置替换认证逻辑
        http.addFilterAt(customAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
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

    /** @Author: MachineGeek
    * @Description: 注册Jackson序列化类
    * @Date: 2020/10/6
    * @param
    * @Return com.fasterxml.jackson.databind.ObjectMapper
    */
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    /** @Author: MachineGeek
    * @Description: 注册认证管理类
    * @Date: 2020/10/6
     * @param
    * @Return org.springframework.security.authentication.AuthenticationManager
    */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
