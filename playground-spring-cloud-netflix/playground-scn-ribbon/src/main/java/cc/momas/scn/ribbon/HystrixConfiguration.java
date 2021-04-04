package cc.momas.scn.ribbon;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

/**
 * Hystrix配置
 *
 * @author Sod-Momas
 * @since 2021-01-30
 */
@Configuration
// 启用hystrix
@EnableHystrix
public class HystrixConfiguration {
}
