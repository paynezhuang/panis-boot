package com.izpan.infrastructure.config;

import com.izpan.common.constants.SystemCacheConstant;
import com.izpan.infrastructure.factory.ObjectMapperFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Map;

/**
 * Redis 配置信息
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @ProjectName panis-boot
 * @ClassName com.izpan.infrastructure.config.RedisConfiguration
 * @CreateTime 2023/7/10 - 21:26
 */

@Slf4j
@Configuration
@EnableCaching
public class RedisConfiguration {

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, T> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 使用 GenericJackson2JsonRedisSerializer 替换默认的 JdkSerializationRedisSerializer 来序列化和反序列化 redis 的 value 值
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = redisSerializer();

        // key 采用 String 的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash 的 key 也采用 String 的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value 序列化方式采用 jackson
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        // hash  序列化方式采用 jackson
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);

        // 初始化 RedisTemplate 对象
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
        // 初始化一个 RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置，如过期时间等
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
                // 不缓存空置
                .disableCachingNullValues()
                // 设置缓存过期时间
                .entryTtl(Duration.ofMinutes(60));

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> customRedisConfigMap = getRedisCacheConfigurationMap(defaultCacheConfig);

        // 创建 RedisCacheManager 对象
        RedisCacheManager build = RedisCacheManager.builder(redisCacheWriter)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(customRedisConfigMap)
                .build();

        log.info("Initializing CacheManager RedisCacheManager Start success.");
        return build;
    }

    /**
     * 自定义的 key 缓存时间
     *
     * @param defaultCacheConfig 默认缓存配置
     * @return {@linkplain Map} 指定的Key默认缓存时间
     * @author payne.zhuang
     * @CreateTime 2024-04-20 22:12
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap(RedisCacheConfiguration defaultCacheConfig) {
        return Map.of(
                // 缓存用户路由 15 天
                SystemCacheConstant.SYSTEM_USER_ROUTE, defaultCacheConfig.entryTtl(Duration.ofDays(15)),
                // 缓存用户角色权限 30 天
                SystemCacheConstant.SYSTEM_ROLE_MENU_LIST, defaultCacheConfig.entryTtl(Duration.ofDays(30)),
                SystemCacheConstant.SYSTEM_ROLE_PERMISSION_LIST, defaultCacheConfig.entryTtl(Duration.ofDays(30)),
                SystemCacheConstant.SYSTEM_ROLE_PERMISSION_RESOURCES, defaultCacheConfig.entryTtl(Duration.ofDays(30))
        );
    }


    /**
     * 创建 RedisSerializer 对象，使用 Jackson 序列化器
     *
     * @return {@link RedisSerializer} 对象
     * @author payne.zhuang
     * @CreateTime 2023-07-10 21:40
     */
    private GenericJackson2JsonRedisSerializer redisSerializer() {
        // 返回配置好的序列化器
        return new GenericJackson2JsonRedisSerializer(ObjectMapperFactory.getCustomObjectMapper());
    }

}