package cc.momas.spring.webflux.r2dbc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 商品业务层
 *
 * @author Sod-Momas
 * @since 2021-02-15
 */
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * 保存商品
     */
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    /**
     * 更新商品
     */
    public Mono<Product> update(Product product) {
        return productRepository.existsById(product.getId())
                .flatMap(p -> {
                    if (p) {
                        return productRepository.save(product);
                    }
                    return Mono.just(product);
                });
    }

    /**
     * 根据商品ID查询商品
     */
    public Mono<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    /**
     * 查询所有商品
     */
    public Flux<Product> findByAll() {
        return productRepository.findAll();
    }

    /**
     * 查询所有商品（分页）
     */
    public Flux<Product> findAllByPage() {
        return productRepository.findAllByPage();
    }

    /**
     * 根据类别查询商品（分页）
     */
    public Flux<Product> findByCategoryPage(ProductCriteria productCriteria) {
        final var category = productCriteria.getCategory();
        int offset = (productCriteria.getPage() - 1) * productCriteria.getPageSize();
        int limit = productCriteria.getPageSize();
        return productRepository.findByCategory(category, offset, limit);
    }

    /**
     * 根据ID删除商品
     */
    public Mono<Void> delete(Integer productId) {
        return productRepository.findById(productId)
                .flatMap(p -> productRepository.delete(p))
                .switchIfEmpty(Mono.empty());
    }

    /**
     * 根据ID删除商品
     */
    public Mono<Void> deleteProduct(Integer productId) {
        return productRepository.existsById(productId)
                .flatMap(p -> {
                    if (p) {
                        return productRepository.deleteById(productId);
                    }
                    return Mono.empty();
                });
    }
}