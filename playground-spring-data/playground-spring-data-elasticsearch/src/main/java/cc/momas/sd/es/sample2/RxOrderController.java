package cc.momas.sd.es.sample2;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * @author Sod-Momas
 * @since 2022/3/27
 */
@RestController
@RequestMapping("rx-order")
public class RxOrderController {
    private Logger log = LoggerFactory.getLogger(RxOrderController.class);
    @Autowired
    private RxOrderRepository rxOrderRepository;
    @Autowired
    private ElasticsearchRestTemplate template;

    @PostConstruct
    public void init() {
        if (!template.indexOps(RxOrder.class).exists()) {
            log.info("index not exist,creating...");
            template.indexOps(RxOrder.class).create();
            template.indexOps(RxOrder.class).createMapping();
            log.info("index created.");
        } else {
            log.info("index exist.");
        }
    }

    @RequestMapping("get")
    Optional<RxOrder> get(@RequestParam("id") Long id) {
        return rxOrderRepository.findById(id);
    }

//    @RequestMapping("search")
//    Iterable<RxOrder> search(@RequestParam("keyword") String keyword) {
//        // 自由构建查询语句
//        final WildcardQueryBuilder query = QueryBuilders.wildcardQuery("rxTitle", keyword);
//        return rxOrderRepository.search(query);
//    }

//    @RequestMapping("native-search")
//    Iterable<RxOrder> nativeSearch(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                   @RequestParam(value = "size", defaultValue = "2") Integer size,
//                                   @RequestParam(value = "keyword") String keyword) {
//        //构建JSON查询条件
//        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//        // 查询条件构建
//        builder.withQuery(QueryBuilders.termQuery("rxTitle,rxDesc", keyword));
//        // 分页
//        builder.withPageable(PageRequest.of(page, size));
//        // 排序
//        builder.withSort(SortBuilders.fieldSort("rxPrice").order(SortOrder.DESC));
//        return rxOrderRepository.nativeSearch(builder);
//    }

    @RequestMapping("page")
    Page<RxOrder> page(@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "2") Integer size,
                       @RequestParam(value = "keyword") String keyword) {
        final Sort sort = Sort.by(Sort.Direction.DESC, "rxPrice");
        final PageRequest pageRequest = PageRequest.of(page, size, sort);
        // TODO 这里怎么构建条件传入呢
        //  final TermQueryBuilder query = QueryBuilders.termQuery("rxTitle,rxDesc", keyword);
        return rxOrderRepository.findAll(pageRequest);
    }

//    @RequestMapping("aggregation")
//    Iterable<RxOrder> aggregation(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                                  @RequestParam(value = "size", defaultValue = "2") Integer size,
//                                  @RequestParam(value = "keyword") String keyword) {
//        //构建JSON查询条件
//        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
//        // 查询条件构建
//        builder.withQuery(QueryBuilders.termQuery("rxTitle,rxDesc", keyword));
//        // 分页
//        builder.withPageable(PageRequest.of(page, size));
//        // 排序
//        builder.withSort(SortBuilders.fieldSort("rxPrice").order(SortOrder.DESC));
//        return rxOrderRepository.nativeSearch(builder);
//    }


    @RequestMapping("find-by-title")
    Iterable<RxOrder> get(@RequestParam("rxTitle") String rxTitle) {
        return rxOrderRepository.findByRxTitle(rxTitle);
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
