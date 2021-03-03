package cc.momas.gw.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Sod-Momas
 * @since 2021-01-30
 */
// 声明定义该类访问的是 playground-scn-eureka-client 服务
@FeignClient(value = "playground-scn-eureka-client", fallback = HiServiceFallback.class)
public interface HiService {
    @RequestMapping(value = "/hi")
    String hi(@RequestParam(value = "name") String name);
}
