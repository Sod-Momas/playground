package cc.momas.sd.es.sample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Sod-Momas
 * @since 2022/3/27
 */
@RestController
@RequestMapping("rx-order")
public class RxOrderController {
    @Autowired
    private RxOrderRepository rxOrderRepository;
    @Autowired
    private ElasticsearchRestTemplate template;

    @RequestMapping("get")
    Optional<RxOrder> get(@RequestParam("id") Long id) {
        return rxOrderRepository.findById(id);
    }

    @RequestMapping("add")
    RxOrder add(@RequestBody RxOrder rxOrder) {
        return rxOrderRepository.save(rxOrder);
    }

    @RequestMapping("create-index")
    boolean createIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
//        template.createIndex(Item.class) ;
        return template.indexOps(RxOrder.class).create();

    }

    @RequestMapping("create-mapping")
    Document createMapping() {
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
//        template.putMapping(Item.class) ;
        return template.indexOps(RxOrder.class).createMapping();
    }

    @RequestMapping("delete-index")
    boolean deleteIndex() {
        return template.indexOps(RxOrder.class).delete();
    }
}
