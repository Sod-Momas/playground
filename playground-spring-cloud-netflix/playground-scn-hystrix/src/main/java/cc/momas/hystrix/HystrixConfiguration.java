package cc.momas.hystrix;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Sod-Momas
 * @since 2021-03-25
 */
@Configuration
public class HystrixConfiguration {
    /**
     * 访问地址 http://localhost:2006/actuator/proxy.stream
     * 解决断路器仪表盘404
     */
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
        final var servlet = new HystrixMetricsStreamServlet();
        final var registrationBean = new ServletRegistrationBean<>(servlet);
        registrationBean.setLoadOnStartup(1);
        // http://localhost:2006/actuator/proxy.stream
        registrationBean.addUrlMappings("/actuator/proxy.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
