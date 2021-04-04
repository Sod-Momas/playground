package cc.momas.scn.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Sod-Momas
 * @since 2021-03-28
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerHaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerHaApplication.class, args);
    }

}
