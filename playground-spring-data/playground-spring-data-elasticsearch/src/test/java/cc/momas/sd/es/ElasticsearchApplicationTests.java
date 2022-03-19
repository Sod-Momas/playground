package cc.momas.sd.es;

import cc.momas.sd.es.sample1.KibanaSampleDataEcommerce;
import cc.momas.sd.es.sample1.KibanaSampleDataEcommerceRepository;
import cc.momas.sd.es.scrollsample.ScrollService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author Sod-Momas
 * @since 2022/3/6
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElasticsearchApplicationTests {
    private final Logger log = LoggerFactory.getLogger(ElasticsearchApplicationTests.class);
    @Autowired
    private KibanaSampleDataEcommerceRepository sampleDataEcommerceRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ScrollService scrollService;


    @Test
    public void loadContext() {
        System.out.println("hello test");
    }

    @Test
    public void sample1GetById() throws JsonProcessingException {
        String id = "5NZkoH8B0m_t-Mq0O_UQ";
        final Optional<KibanaSampleDataEcommerce> optional = sampleDataEcommerceRepository.findById(id);
        final String json = objectMapper.writeValueAsString(optional.orElseThrow());
        System.out.println(json);
    }

    @Test
    public void sample2ScrollAll1() throws IOException {
        int SIZE = 10;
        int total = 0;
        Map<String, Object> result = scrollService.queryScrollFirstPage(SIZE, null, new String[]{"kibana_sample_data_ecommerce"});
        while (true) {
            String nextScrollId = (String) result.get("scrollId");
            log.info("下一页的scrollId：{}", nextScrollId);
            SearchHits hits = (SearchHits) result.get("hits");
            if (log.isDebugEnabled()) {
                log.info("[EtlAliyunServiceImpl] 查询完成,查询结果：{}", result);
            } else {
                log.info("[EtlAliyunServiceImpl] 查询完成");
            }
            final SearchHit[] hitsArray = hits.getHits();
            if (hitsArray == null || hitsArray.length == 0) {
                log.info("[EtlAliyunServiceImpl] 没有查询到数据，结束流程");
                break;
            }
            for (SearchHit hit : hitsArray) {
                total++;
                final Map<String, Object> data = hit.getSourceAsMap();
                final String json = objectMapper.writeValueAsString(data);
                log.info(json);
            }
            result = scrollService.queryScrollNextPage(nextScrollId);
        }
        log.info("all count:{}", total);
    }
}
