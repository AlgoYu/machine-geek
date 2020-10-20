package cn.machine.geek.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: MachineGeek
 * @Description: LocalDateTime序列化配置类
 * @Date: 2020/10/20
 */
@Configuration
public class LocalDateTimeSerializerConfig {
    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    /** @Author: MachineGeek
    * @Description: LocalDateTime序列化器
    * @Date: 2020/10/20
     * @param
    * @Return com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
    */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }
    /** @Author: MachineGeek
    * @Description: LocalDateTime反序列化器
    * @Date: 2020/10/20
     * @param
    * @Return com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
    */
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }
    /** @Author: MachineGeek
    * @Description: 注册到Jackson构建器里
    * @Date: 2020/10/20
     * @param
    * @Return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
    */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        //这种方式同上
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
                jacksonObjectMapperBuilder.deserializerByType(LocalDateTime.class,localDateTimeDeserializer());
                jacksonObjectMapperBuilder.simpleDateFormat(pattern);
            }
        };
    }
}
