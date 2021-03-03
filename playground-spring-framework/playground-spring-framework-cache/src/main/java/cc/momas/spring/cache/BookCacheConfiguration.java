package cc.momas.spring.cache;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * 缓存器配置
 *
 * @author Sod-Momas
 * @since 2021.02.21
 */
@EnableCaching // 启用缓存
@Configuration // 启用配置
public class BookCacheConfiguration {

    /**
     * redis 默认缓存策略
     *
     * @param properties redis 缓存配置
     * @return 缓存策略
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration(RedisProperties properties) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(properties.getTimeout());
    }

    /**
     * redis 缓存接口配置, 配置存取缓存的默认行为
     *
     * @param redisConnectionFactory redis 连接工厂
     * @param properties             redis 配置
     * @return 缓存行为管理器
     */
    @Bean("cacheManager")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
                                     RedisProperties properties) {
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration(properties))
                .build();
    }
}
