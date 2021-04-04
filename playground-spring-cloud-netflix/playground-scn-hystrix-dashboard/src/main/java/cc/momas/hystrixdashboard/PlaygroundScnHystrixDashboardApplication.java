package cc.momas.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Sod-Momas
 * @since 2021.03.25
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScnHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScnHystrixDashboardApplication.class, args);
    }

}
