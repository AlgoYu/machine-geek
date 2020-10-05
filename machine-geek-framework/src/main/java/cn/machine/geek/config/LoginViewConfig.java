package cn.machine.geek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: MachineGeek
 * @Description: 登录页配置
 * @Date: 2020/10/4
 */
@Configuration
public class LoginViewConfig implements WebMvcConfigurer {
    /** @Author: MachineGeek
     * @Description: 视图控制器
     * @Date: 2020/10/4
     * @param registry
     * @Return void
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/login.html");
        registry.addViewController("/login.html").setViewName("login");
    }
}
