package cc.momas.scn.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sod-Momas
 * @since 2021-01-30
 */
@Service
public class HiService {

    @Autowired
    private RestTemplate restTemplate;

    public String hi(String name) {
        String param;
        if (name == null) {
            param = "";
        } else {
            param = name;
        }
        return restTemplate.getForObject("http://PLAYGROUND-SCN-EUREKA-CLIENT/hi?name=" + param, String.class);
    }

    // 启用hystrix, sayError是后面的方法名
    @HystrixCommand(fallbackMethod = "sayError")
    public String say(String name) {
        if (System.currentTimeMillis() % 2 == 0) {
            // 模拟处理失败的情况
            throw new RuntimeException("test error");
        }
        String param;
        if (name == null) {
            param = "";
        } else {
            param = name;
        }
        return restTemplate.getForObject("http://PLAYGROUND-SCN-EUREKA-CLIENT/hi?name=" + param, String.class);
    }

    public String sayError(String name) {
        return "{\"msg\":null,\"error\":\"sorry, out of service\"}";
    }
}
