package cc.momas.sd.es.sample1;

import cc.momas.sd.es.sample1.KibanaSampleDataEcommerce;
import cc.momas.sd.es.sample1.KibanaSampleDataEcommerceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Sod-Momas
 * @since 2022/3/19
 */
@RestController
@RequestMapping("sd-elastic-test")
public class ElasticTestController {
    @Autowired
    private KibanaSampleDataEcommerceRepository sampleRepository;

    @RequestMapping("test")
    public KibanaSampleDataEcommerce test(String id) {
        final Optional<KibanaSampleDataEcommerce> entity = sampleRepository.findById(id);
        return entity.orElseThrow();
    }
}
