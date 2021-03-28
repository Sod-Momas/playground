package cc.momas.spring.webflux.r2dbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 商品处理器（类似controller）
 *
 * @author Sod-Momas
 * @since 2021-02-15
 */
@Component
public class ProductWebHandler {

    @Autowired
    private ProductService productService;
    @Autowired
    private TransactionalOperator transactionalOperator;
    @Autowired
    private WebClient.Builder builder;

//    public Mono<ServerResponse> findStock(ServerRequest serverRequest) {
//        var productId = Integer.parseInt(serverRequest.pathVariable("productId"));
//        return builder.build()
//                .get()
//                .uri("/stock/get/{productId}", productId)
//                .retrieve().bodyToMono(String.class)
//                .flatMap(p -> ServerResponse.ok().bodyValue(p));
//    }

    /**
     * 保存商品
     */
    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Product.class)
                .flatMap(i -> productService.save(i))
                .flatMap(p -> ServerResponse.ok().bodyValue(p));
    }

    /**
     * 保存多个商品
     */
//    @Transactional // 注解开启事务
    public Mono<ServerResponse> saveMany(ServerRequest serverRequest) {
        return serverRequest.bodyToFlux(Product.class)
                .flatMap(i -> productService.save(i))
                .then(ServerResponse.ok().build())
                //手动使用事务
                .as(transactionalOperator::transactional)
                ;
    }

    /**
     * 查询商品
     */
    public Mono<ServerResponse> find(ServerRequest serverRequest) {
        var productId = Integer.parseInt(serverRequest.pathVariable("productId"));
        return productService
                .findById(productId)
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    /**
     * 查询所有
     */
    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(productService.findByAll(), Product.class);
    }

    public Mono<ServerResponse> findByCategoryPage(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductCriteria.class)
                .flatMap(p -> ServerResponse.ok()
                        .body(productService.findByCategoryPage(p), Product.class))
                ;
    }

    public Mono<ServerResponse> findAllByPage(ServerRequest serverRequest) {
        return ServerResponse.ok().body(productService.findAllByPage(), Product.class);
    }

    /**
     * 更新商品
     */
    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        var productId = Integer.parseInt(serverRequest.pathVariable("productId"));
        return productService
                .findById(productId)
                .flatMap(p -> serverRequest.bodyToMono(Product.class)
                        .flatMap(i -> productService.save(i)))
                .flatMap(p -> ServerResponse.ok().bodyValue(p))
                .switchIfEmpty(ServerResponse.notFound().build())
                ;
    }


    /**
     * 删除商品
     */
    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        var productId = Integer.parseInt(serverRequest.pathVariable("productId"));
        return productService
                .findById(productId)
                .flatMap(p -> productService.delete(p.getId()).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}