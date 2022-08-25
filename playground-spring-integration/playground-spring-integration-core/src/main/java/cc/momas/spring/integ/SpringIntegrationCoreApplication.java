package cc.momas.spring.integ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * @author Sod-Momas
 * @since 2022/8/25
 */
@SpringBootApplication
@ImportResource("/integration/integration.xml")
public class SpringIntegrationCoreApplication {
    public static void main(String[] args) throws IOException {
//        SpringApplication.run(SpringIntegrationCoreApplication.class, args);
        ConfigurableApplicationContext ctx = new SpringApplication(SpringIntegrationCoreApplication.class).run(args);
        System.out.println("Hit Enter to terminate");
        System.in.read();
        ctx.close();
    }
}
