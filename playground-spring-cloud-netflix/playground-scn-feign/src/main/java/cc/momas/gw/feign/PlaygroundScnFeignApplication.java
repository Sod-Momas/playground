package cc.momas.gw.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Sod-Momas
 * @since 2021.01.30
 */
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScnFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScnFeignApplication.class, args);
    }

}
