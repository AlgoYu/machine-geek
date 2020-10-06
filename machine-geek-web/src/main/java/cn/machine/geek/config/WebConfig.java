package cn.machine.geek.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: MachineGeek
 * @Description: Web服务器配置类
 * @Date: 2020/10/4
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "upload")
public class WebConfig implements WebMvcConfigurer {
    // 映射路径
    private String urlPath;
    // 本地路径
    private String localPath;

    /** @Author: MachineGeek
    * @Description: 跨域配置
    * @Date: 2020/10/4
    * @param registry
    * @Return void
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    /** @Author: MachineGeek
    * @Description: 静态资源配置
    * @Date: 2020/10/4
    * @param registry
    * @Return void
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 上传路径映射
        registry.addResourceHandler(this.urlPath+"**")
                .addResourceLocations("file:"+this.localPath);
        // 静态资源映射
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
