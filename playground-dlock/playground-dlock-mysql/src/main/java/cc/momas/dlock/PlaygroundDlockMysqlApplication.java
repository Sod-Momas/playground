package cc.momas.dlock;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Sod-Momas
 * @since 2021.02.06
 */
@SpringBootApplication
public class PlaygroundDlockMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundDlockMysqlApplication.class, args);
    }

    @Bean
    ApplicationRunner init() {
        return (a) -> DataSources.init();
    }
}
