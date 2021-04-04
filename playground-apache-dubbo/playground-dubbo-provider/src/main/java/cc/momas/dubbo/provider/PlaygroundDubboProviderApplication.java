package cc.momas.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author Sod-Momas
 * @since 2021.02.13
 */
@EnableDubbo // 启用dubbo，等同于配置 dubbo.scan.base-packages
@SpringBootApplication
public class PlaygroundDubboProviderApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(PlaygroundDubboProviderApplication.class, args);
        System.in.read();
    }
}
