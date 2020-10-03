package cn.machine.geek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* @Author: MachineGeek
* @Description: 安全配置类
* @Date: 2020/10/3
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    /** @Author: MachineGeek
    * @Description: 配置用户细节类和加密器
    * @Date: 2020/10/3
     * @param auth
    * @Return void
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    /** @Author: MachineGeek
    * @Description: 配置忽略路径
    * @Date: 2020/10/3
     * @param web
    * @Return void
    */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/doc.html","/static/**");
    }

    /** @Author: MachineGeek
     * @Description: 配置认证路径
     * @Date: 2020/10/3
     * @param http
     * @Return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单登录
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        // 其他路径都需要认证
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
