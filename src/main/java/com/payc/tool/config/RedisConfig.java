package com.payc.tool.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yangshubao
 * Created on 2021/8/3
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.database}")
    private int redisDatabase;

    @Value("${spring.redis.database2}")
    private int redisDatabase2;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisConfig.setPassword(redisPassword);
        redisConfig.setDatabase(redisDatabase);
        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean(name = "redisConnectionFactory2")
    public RedisConnectionFactory redisConnectionFactory2() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(redisHost, redisPort);
        redisConfig.setPassword(redisPassword);
        redisConfig.setDatabase(redisDatabase2);
        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // redis的键采用String的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // redis的值采用jackson的序列化方式
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // hash的值采用jackson的序列化方式
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = "secondRedisTemplate")
    public RedisTemplate<String, Object> secondRedisTemplate(@Qualifier("redisConnectionFactory2") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // redis的键采用String的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // redis的值采用jackson的序列化方式
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        // hash的key也采用String的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // hash的值采用jackson的序列化方式
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
