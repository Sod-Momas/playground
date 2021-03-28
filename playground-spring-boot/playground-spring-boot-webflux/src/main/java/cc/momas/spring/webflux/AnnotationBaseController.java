package cc.momas.spring.webflux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 基于注解的接口
 *
 * @author Sod-Momas
 * @since 2021-02-15
 */
@RestController
@RequestMapping(value = "/anno")
public class AnnotationBaseController {
    @RequestMapping("/get")
    public Mono<String> get() {
        return Mono.just("hello 基于注解的weblux");
    }
}
