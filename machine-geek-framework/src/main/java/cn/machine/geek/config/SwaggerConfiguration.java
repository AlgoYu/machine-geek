package cn.machine.geek.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author: MachineGeek
 * @Description: API注解配置类
 * @Date: 2020/10/3
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    /** @Author: MachineGeek
    * @Description: 文档配置
    * @Date: 2020/10/3
     * @param
    * @Return springfox.documentation.spring.web.plugins.Docket
    */
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("cn.machine.geek.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /** @Author: MachineGeek
    * @Description: 文档信息
    * @Date: 2020/10/3
     * @param
    * @Return springfox.documentation.service.ApiInfo
    */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("MachineGeek","machine-geek.cn","794763733@qq.com");
        return new ApiInfo("快速开发平台","系统API文档","1.0","暂无",contact,"暂无","暂无",new ArrayList<>());
    }
}
