# spring-boot-cache

spring 缓存测试示例

# 配置关键

1. 注解配置

注解         | 作用
-------------|---
@CachePut    | 根据方法参数添加进缓存
@Cacheable   | 根据方法返回值添加进缓存
@CacheEvict  | 根据方法参数清除缓存
@CacheConfig | 作用于类上, 表示该类所有缓存注解的默认配置
@Caching     | 作用于方法上, 给方法添加多个缓存动作时使用

我觉得吧, 这几个注解的强大之后在于支持 SpEL, 如果熟悉 SpEL 的话, 这几个注解已经能满足绝大多数业务缓存需求了 

2. 如果使用 java 内存缓存, 则不需要其他配置了, 如果使用 redis 中间件缓存, 则需要配置中间件参数

```properties
# 选择 redis 类型缓存, 表示缓存内容要存到 redis
spring.cache.type=redis
# redis 连接, 需要密码则输入密码
spring.redis.port=6379
spring.redis.host=localhost
spring.redis.password=
spring.redis.timeout=10s
```

3. 在配置完 redis 后, 需要配置一下默认的缓存管理机制

```java

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisProperties.class)
public class BookCacheConfiguration {
    /**
     * redis 连接工厂, 主要用于选择客户端
     *
     * @param properties redis 配置
     * @return 连接工厂
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisProperties properties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        return new LettuceConnectionFactory(config);
    }

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
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
                                     RedisProperties properties) {
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration(properties))
                .build();
    }
}
```

这样就可以启用 redis 缓存了

# 参考资料

- [A Guide To Caching in Spring](https://www.baeldung.com/spring-cache-tutorial)
- [Caching Data with Spring](https://spring.io/guides/gs/caching/)
- [33. Caching](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-caching.html#boot-features-caching-provider-generic)
- [史上最全的Spring Boot Cache使用与整合](https://www.cnblogs.com/yueshutong/p/9381540.html)
- [redis在spring中的详细配置](https://www.jianshu.com/p/ad2ac7412b3c)
- [Spring Cache与Redis结合使用](https://blog.csdn.net/guijiaoba/article/details/78574600)
- [spring boot + spring cache 实现两级缓存（redis + caffeine）](https://my.oschina.net/dengfuwei/blog/1616221)