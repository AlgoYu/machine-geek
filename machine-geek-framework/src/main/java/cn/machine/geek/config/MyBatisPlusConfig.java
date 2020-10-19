package cn.machine.geek.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: MachineGeek
 * @Description: MyBatis Plus配置类
 * @Date: 2020/10/19
 */
@Configuration
public class MyBatisPlusConfig {
    /** @Author: MachineGeek
    * @Description: 配置分页插件
    * @Date: 2020/10/19
     * @param
    * @Return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
    */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }
}
