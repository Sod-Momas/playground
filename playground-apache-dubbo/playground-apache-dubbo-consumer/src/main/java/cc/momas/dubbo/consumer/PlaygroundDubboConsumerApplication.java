package cc.momas.dubbo.consumer;

import cc.momas.dubbo.entity.MomasAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.UUID;

@SpringBootApplication
public class PlaygroundDubboConsumerApplication {
    private static final Logger log = LoggerFactory.getLogger(PlaygroundDubboConsumerApplication.class);

    public static void main(String[] args) throws IOException {

//        System.setProperty("dubbo.mapping.cache.filePath", System.getProperty("user.home"));
//        System.setProperty("dubbo.mapping.cache.fileName", UUID.randomUUID().toString());
//        log.info("dubbo.mapping.cache.filePath={}", System.getProperty("dubbo.mapping.cache.filePath"));
//        log.info("dubbo.mapping.cache.fileName={}", System.getProperty("dubbo.mapping.cache.fileName"));

        final ConfigurableApplicationContext context = SpringApplication.run(PlaygroundDubboConsumerApplication.class, args);
        log.info("consumer stopped.");
        final AccountController controller = context.getBean(AccountController.class);
//        final String hello = controller.hello();
//        log.info("hello  {}", hello);
        final MomasAccount right = controller.rightLogin();
        log.info("right  {}", right);
        try {
            // 会抛异常
            controller.errorLogin();
        } catch (Exception e) {
            log.info("errorLogin", e);
        }
        System.in.read();
    }

//    @Bean
//    ApplicationRunner run(AccountController controller) {
//        return (arg) -> {
//            final String hello = controller.hello();
//            log.info("{}", hello);
//            final MomasAccount right = controller.rightLogin();
//            log.info("{}", right);
//            try {
//                // 会抛异常
//                controller.errorLogin();
//            } catch (Exception e) {
//                log.info("{}", e);
//            }
//        };
//    }
}
