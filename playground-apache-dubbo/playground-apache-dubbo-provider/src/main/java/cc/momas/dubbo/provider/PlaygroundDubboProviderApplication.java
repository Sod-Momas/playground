package cc.momas.dubbo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Sod-Momas
 * @since 2021.02.13
 */

@SpringBootApplication
public class PlaygroundDubboProviderApplication {
    private static final Logger log = LoggerFactory.getLogger(PlaygroundDubboProviderApplication.class);

    public static void main(String[] args) throws IOException {
//        // #https://github.com/apache/dubbo/issues/9891
//        log.info("user.home={}",System.getProperty("user.home"));
//        System.setProperty("user.home", Objects.requireNonNull(PlaygroundDubboProviderApplication.class.getResource("/")).toString());
//        log.info("user.home={}",System.getProperty("user.home"));
//
//        // 启动内置 zookeeper
        new EmbeddedZooKeeper(2181, false).start();
        SpringApplication.run(PlaygroundDubboProviderApplication.class, args);
        log.info("dubbo service started");
    }
}
