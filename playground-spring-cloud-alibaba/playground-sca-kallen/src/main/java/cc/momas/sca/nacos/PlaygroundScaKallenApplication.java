package cc.momas.sca.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PlaygroundScaKallenApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScaKallenApplication.class, args);
    }

}
