package cc.momas.scn.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 通过@EnableDiscoveryClient向服务中心注册；
 * @author Sod-Momas
 * @since 2021.01.30
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScnRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScnRibbonApplication.class, args);
    }

    /**
     * 通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能
     * @return RestFul http工具类
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
