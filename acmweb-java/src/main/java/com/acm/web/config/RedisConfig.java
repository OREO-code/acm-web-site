package com.acm.web.config;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.database.db1}")
    private int db1;

    @Value("${spring.redis.database.db2}")
    private int db2;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.username}")
    private String username;

    private static final long TIMEOUT = 6000;

    private static final int MAX_Total = 8;

    private static final int MAX_IDLE = 10;

    private static final int MIN_IDLE = 2;

    private static final int MAX_WAIT = -1;

    @Bean
    public GenericObjectPoolConfig getPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(MAX_Total);
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMinIdle(MIN_IDLE);
        poolConfig.setMaxWait(Duration.ofDays(MAX_WAIT));
        return poolConfig;
    }

    @Bean(name = "redisTemplate1")
    public StringRedisTemplate getRedisTemplate1() {
        return getStringRedisTemplate(db1);
    }

    @Bean(name = "redisTemplate2")
    public StringRedisTemplate getRedisTemplate2() {
        return getStringRedisTemplate(db2);
    }

    private StringRedisTemplate getStringRedisTemplate(int database) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        config.setPassword(RedisPassword.of(password));
        config.setUsername(username);
        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(TIMEOUT))
                .poolConfig(getPoolConfig())
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        factory.setDatabase(database);
        factory.afterPropertiesSet();
        return new StringRedisTemplate(factory);
    }
}

