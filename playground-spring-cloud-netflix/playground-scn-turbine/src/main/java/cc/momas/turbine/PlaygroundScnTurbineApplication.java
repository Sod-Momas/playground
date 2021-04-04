package cc.momas.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Sod-Momas
 * @since 2021-03-26
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScnTurbineApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScnTurbineApplication.class, args);
    }
}
