package cn.machine.geek.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: MachineGeek
 * @Description: 代码生成器配置
 * @Date: 2020/11/5
 */
@Configuration
public class MyBatisGeneratorConfig {
    @Value("${generatorPath}")
    private String generatorPath;
    /**
    * @Author: MachineGeek
    * @Description: 注册代码生成器
    * @Date: 5:29 下午
     * @param
    * @Return: com.baomidou.mybatisplus.generator.AutoGenerator
    */
    public AutoGenerator autoGenerator(){
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        return autoGenerator;
    }
}
