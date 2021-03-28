package cc.momas.spring.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**
 * 基于java配置的接口配置，主要内容为路由
 *
 * @author Sod-Momas
 * @since 2021-02-15
 */
@Component
public class JavaConfigBaseController {

    @Bean
    public RouterFunction<ServerResponse> route(JavaConfigBaseController javaConfigBaseController) {
        // GET http://localhost:8080/java-cfg/get
        final RequestPredicate key = RequestPredicates.GET("/java-cfg/get")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN));

        return RouterFunctions.route(key, javaConfigBaseController::get);
    }

    /**
     * 接口
     *
     * @param serverRequest 请求体
     * @return 响应体
     */
    private Mono<ServerResponse> get(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromValue("hello Component webflux"));
    }
}
