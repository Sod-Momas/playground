package cc.momas.scp.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Sod-Momas
 * @since 2021.01.30
 */
// 开启配置中心服务端
@EnableConfigServer
@SpringBootApplication
public class PlaygroundScpConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundScpConfigServerApplication.class, args);
    }

}
