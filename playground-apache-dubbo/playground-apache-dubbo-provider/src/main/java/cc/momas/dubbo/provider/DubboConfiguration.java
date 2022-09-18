package cc.momas.dubbo.provider;

import org.springframework.context.annotation.Configuration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * @author Sod-Momas
 * @since 2022/9/18
 */
@Configuration(proxyBeanMethods = false)
@EnableDubbo
public class DubboConfiguration {
}
