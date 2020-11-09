package cn.machine.geek.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private final String author = "MachineGeek";
    /**
    * @Author: MachineGeek
    * @Description: 注册代码生成器
    * @Date: 5:29 下午
     * @param
    * @Return: com.baomidou.mybatisplus.generator.AutoGenerator
    */
    @Bean
    public AutoGenerator autoGenerator(){
        AutoGenerator autoGenerator = new AutoGenerator();
        // 设置全局配置
        autoGenerator.setGlobalConfig(this.globalConfig());
        // 设置生成引擎
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        // 设置包配置
        autoGenerator.setPackageInfo(this.packageConfig());
        // 设置数据源
        autoGenerator.setDataSource(this.dataSourceConfig());
        // 设置生成策略
        autoGenerator.setStrategy(this.strategyConfig());
        return autoGenerator;
    }

    /**
    * @Author: MachineGeek
    * @Description: 包配置
    * @Date: 11:38 上午
     * @param
    * @Return: com.baomidou.mybatisplus.generator.config.PackageConfig
    */
    private PackageConfig packageConfig(){
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("cn.machine.geek");
        return packageConfig;
    }

    /**
    * @Author: MachineGeek
    * @Description: 全局配置
    * @Date: 11:36 上午
     * @param
    * @Return: com.baomidou.mybatisplus.generator.config.GlobalConfig
    */
    private GlobalConfig globalConfig(){
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(this.author);
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setOutputDir(System.getProperty("user.dir") + this.generatorPath);
        return globalConfig;
    }

    /**
    * @Author: MachineGeek
    * @Description: 数据源配置
    * @Date: 11:21 上午
     * @param
    * @Return: com.baomidou.mybatisplus.generator.config.DataSourceConfig
    */
    private DataSourceConfig dataSourceConfig(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(this.url);
        dataSourceConfig.setDriverName(this.driverName);
        dataSourceConfig.setUsername(this.username);
        dataSourceConfig.setPassword(this.password);
        return dataSourceConfig;
    }

    /**
    * @Author: MachineGeek
    * @Description: 策略配置
    * @Date: 11:28 上午
     * @param
    * @Return: com.baomidou.mybatisplus.generator.config.StrategyConfig
    */
    private StrategyConfig strategyConfig(){
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        return strategyConfig;
    }
}
