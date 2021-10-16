package cc.momas.autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Sod-Momas
 * @since 2021/10/15
 */
@SpringBootApplication
public class SpringBootAutoConfigApplication {
    public static void main(String[] args) {
        System.out.println("main " + SpringBootAutoConfigApplication.class.getClassLoader());
        final ConfigurableApplicationContext context = SpringApplication.run(SpringBootAutoConfigApplication.class, args);
        System.out.println("context :" +  context.getClassLoader());

    }
}
