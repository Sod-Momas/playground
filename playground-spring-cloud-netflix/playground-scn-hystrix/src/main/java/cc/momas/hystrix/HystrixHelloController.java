package cc.momas.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Sod-Momas
 * @since 2021-03-25
 */
@RestController
public class HystrixHelloController {
    @Value("${server.port}")
    String port;
    private static final Random RANDOM = new SecureRandom();

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "sod") String name) {
        // 随机发生异常
        if (RANDOM.nextInt() % 3 == 0) {
            throw new RuntimeException();
        }
        return "hi " + name + " ,i am from port:" + port;
    }
    // 发生异常后会进这个方法
    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }
}
