package cc.momas.scn.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Sod-Momas
 * @since 2021.01.30
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class TeririApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeririApplication.class, args);
    }

}
