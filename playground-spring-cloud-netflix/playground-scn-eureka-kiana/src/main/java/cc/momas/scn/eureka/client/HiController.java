package cc.momas.scn.eureka.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Collections;

/**
 * @author Sod-Momas
 * @since 2021-01-30
 */
@RestController
public class HiController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public Object hi(@RequestParam("name") String name) {
        if (new SecureRandom().nextBoolean()) {
            throw new RuntimeException(name);
        }
        final String msg = name == null ? "this is kiana" : (name + " from " + serverPort);
        return Collections.singletonMap("msg", msg);
    }

    /**
     * hystrix检测到异常后会调用这个方法来返回正确的结果
     *
     * @param name 原方法的参数
     * @return 因为正确的结果
     * @see #hi(String)
     */
    Object hiError(String name) {
        return Collections.singletonMap("msg", "opssss. error happen," + name + " from " + serverPort);
    }

}
