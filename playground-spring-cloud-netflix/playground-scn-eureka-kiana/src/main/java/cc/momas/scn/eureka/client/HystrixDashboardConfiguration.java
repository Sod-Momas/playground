package cc.momas.scn.eureka.client;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * hystrix stream 配置
 *
 * @author Sod-Momas
 * @since 2021-03-26
 */
@Configuration
@EnableHystrix
@EnableCircuitBreaker
public class HystrixDashboardConfiguration {

    /**
     * 访问地址 http://localhost:2006/actuator/hystrix.stream
     * 解决断路器仪表盘404
     */
    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
        HystrixMetricsStreamServlet servlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(servlet);
        registrationBean.setLoadOnStartup(1);
        // http://localhost:2002/actuator/hystrix.stream
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
