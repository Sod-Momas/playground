package cc.momas.sd.es.sample1;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sod-Momas
 * @since 2022/3/19
 */
@Repository
public interface KibanaSampleDataEcommerceRepository extends CrudRepository<KibanaSampleDataEcommerce, String> {
}
