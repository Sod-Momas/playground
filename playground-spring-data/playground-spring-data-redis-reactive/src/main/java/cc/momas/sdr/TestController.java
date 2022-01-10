package cc.momas.sdr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Sod-Momas
 * @since 2022/1/9
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;
    @GetMapping("/hello")
    public Mono<String> hello(@RequestParam("name") String name) {
        return Mono.just("hello" + name);
    }

    @GetMapping("/redis-get")
    public Mono<String> redisGet(@RequestParam("key") String key) {
        return reactiveStringRedisTemplate.opsForValue().get(key);
    }

    @GetMapping("/redis-add")
    public Mono<Boolean> redisGet(@RequestParam("key") String key, @RequestParam("value") String value) {
        return reactiveStringRedisTemplate.opsForValue().set(key, value);
    }
}
