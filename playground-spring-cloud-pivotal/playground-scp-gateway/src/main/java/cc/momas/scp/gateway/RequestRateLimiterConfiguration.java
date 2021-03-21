package cc.momas.scp.gateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.PrincipalNameKeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 限流规则
 *
 * @author Sod-Momas
 * @since 2021-03-21
 */
@Configuration
public class RequestRateLimiterConfiguration {
    /**
     * 根据用户限流
     *
     * @return 限流规则-用户维度
     */
//    @Bean
    public PrincipalNameKeyResolver principalNameKeyResolver() {
        return new PrincipalNameKeyResolver();
    }

    /**
     * 根据请求路径进行限流
     *
     * @return 限流规则-请求路径
     */
    @Bean("requestRateLimiter")
    public KeyResolver uriKeyResolver() {
        // Mono.just(exchange.getRequest().getQueryParams().getFirst("user")); // 根据某个参数限流
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
    }
}
