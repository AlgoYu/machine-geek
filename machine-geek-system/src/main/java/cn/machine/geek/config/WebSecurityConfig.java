package cn.machine.geek.config;

import cn.machine.geek.security.CustomAuthenticationFilter;
import cn.machine.geek.security.TokenAuthenticationFilter;
import cn.machine.geek.service.ITokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    // 自定义Token过滤器
    @Autowired
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    // 自定义未登陆逻辑
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    // 自定义访问拒绝逻辑
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    // 自定义注销逻辑
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    // Token服务
    @Autowired
    private ITokenService tokenService;
    // Jackson实例
    @Autowired
    private ObjectMapper objectMapper;
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
                .logoutSuccessHandler(this.logoutSuccessHandler)
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

        // 设置自定义验证逻辑
        http.addFilterAt(this.customAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
        // 设置Token过滤器
        http.addFilterBefore(this.tokenAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
    }

    /** @Author: MachineGeek
    * @Description: 自定义验证逻辑
    * @Date: 2020/10/17
    * @param
    * @Return cn.machine.geek.security.CustomAuthenticationFilter
    */
    private CustomAuthenticationFilter customAuthenticationFilter(){
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(this.tokenService,this.objectMapper);
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        try {
            customAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customAuthenticationFilter;
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
