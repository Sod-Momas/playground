package cc.momas.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Sod-Momas
 * @since 2021/10/15
 */
@Component
public class TestService implements ApplicationRunner {
    @Autowired
    private MyStarter myStarter;

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("autowired success:" + myStarter);
    }
}
