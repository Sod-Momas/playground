package cc.momas.sd.es.sample2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sod-Momas
 * @since 2022/3/27
 */
@Repository
public interface RxOrderRepository extends CrudRepository<RxOrder, Long> {
}
