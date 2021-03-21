package cc.momas.scp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 网关服务
 *
 * @author Sod-Momas
 * @since 2021.03.21
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScpGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScpGatewayApplication.class, args);
    }

}
