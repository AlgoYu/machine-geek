package cn.machine.geek.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: MachineGeek
 * @Description: Redis配置类
 * @Date: 2020/10/6
 */
@Configuration
public class RedisConfig {
    /**
    * @Author: MachineGeek
    * @Description: 注册RedisTemplate实例并设置序列化方式
    * @Date: 2020/10/12 15:36
    * @param factory: 
    * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
    */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建RedisTemplate对象
        RedisTemplate<String,Object> template = new RedisTemplate<String,Object>();

        // 设置连接工厂
        template.setConnectionFactory(factory);

        // 创建Json序列化方式
        Jackson2JsonRedisSerializer<Object> jacksonSerializer = this.serializer();

        // 创建String序列化方式
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 设置Value和Hash的key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        // 设置Value采用Json序列化
        template.setValueSerializer(jacksonSerializer);

        // 设置HashValue采用String序列化
        template.setHashValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
    
    /**
    * @Author: MachineGeek
    * @Description: 创建Json序列化对象
    * @Date: 2020/10/12 16:04
    * @return: org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer<java.lang.Object>
    */
    private Jackson2JsonRedisSerializer<Object> serializer() {
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();

        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        // 忽略未知属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 使用Jackson
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
