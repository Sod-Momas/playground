package cc.momas.scn.gw.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Sod-Momas
 * @since 2021.01.30
 */
// 开启 Zuul
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScnZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScnZuulApplication.class, args);
    }

}
