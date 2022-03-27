package cc.momas.sd.es.sample2;

import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sod-Momas
 * @since 2022/3/27
 */
@Repository
public interface RxOrderRepository extends PagingAndSortingRepository<RxOrder, Long> {
    /** spring data 根据方法名生成查询逻辑 */
    Iterable<RxOrder> findByRxTitle(String title);

//    /** 高级查询 */
//    Iterable<RxOrder> search(WildcardQueryBuilder query);
//    /** 自定义查询 */
//    Iterable<RxOrder> nativeSearch(NativeSearchQueryBuilder builder);
//    /** 分页查询 */
//    Page<RxOrder> find(PageRequest pageRequest, FieldSortBuilder sort, TermQueryBuilder query);
}
