package cn.machine.geek.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: MachineGeek
 * @Description: Web服务器配置类
 * @Date: 2020/10/4
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 映射路径
    public static String URL_PATH;
    // 本地路径
    public static String LOCAL_PATH;

    @Value("${upload.urlPath}")
    public static void setUrlPath(String urlPath) {
        URL_PATH = urlPath;
    }

    @Value("${upload.localPath}")
    public static void setLocalPath(String localPath) {
        LOCAL_PATH = localPath;
    }

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
        registry.addResourceHandler(this.URL_PATH +"**")
                .addResourceLocations("file:"+this.LOCAL_PATH);
        // 静态资源映射
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
