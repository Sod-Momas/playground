package cc.momas.spring.webflux.r2dbc;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 商品DAO层
 *
 * @author Sod-Momas
 * @since 2021-02-15
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {
    /**
     * 根据要分类分页查找商品
     *
     * @param category 分类名
     * @param offset   偏移量
     * @param limit    每页容量
     * @return 商品列表
     */
    @Query("SELECT * FROM t_product WHERE category = :category limit :offset,:limit")
    Flux<Product> findByCategory(String category, int offset, int limit);

    /**
     * 查找所有商品
     *
     * @return 所有商品
     */
    @Query("select id,name,price,stock,category from t_product")
    Flux<Product> findAllByPage();
}
