package cc.momas.gw.feign;

import org.springframework.stereotype.Component;

/**
 * HiService 调用出错时使用该类进行回落处理
 *
 * @author Sod-Momas
 * @since 2021-01-30
 */
@Component
public class HiServiceFallback implements HiService {
    @Override
    public String hi(String name) {
        return "{\"msg\":null,\"error\":\"sorry, out of service\"}";
    }
}
