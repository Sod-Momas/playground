package cc.momas.sd.es.indices;

import cc.momas.sd.es.ESMappingUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sod-Momas
 * @since 2022/10/14
 */

@RestController
@RequestMapping("mapping")
public class MappingController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // http://localhost:8080/mapping/exist?idx=momas_order
    @RequestMapping("exist")
    Boolean exist(@RequestParam("idx") String idx) {
        // 存在返回true,不存在返回false
        return ESMappingUtil.isExistsIndex(restHighLevelClient, idx);
    }

    // http://localhost:8080/mapping/create-if-not-exist?idx=momas_order
    @RequestMapping("create-if-not-exist")
    Boolean createIfNotExist(@RequestParam("idx") String idx) throws IOException {
        // 读取mapping配置
        final InputStream is = new ClassPathResource("mapping/" + idx + ".json").getInputStream();
        final String settings = new String(is.readAllBytes());
        // 写入ElasticSearch
        return ESMappingUtil.createIfNoExist(restHighLevelClient, idx, settings);
    }

    // http://localhost:8080/mapping/delete-index?idx=momas_order
    @RequestMapping("delete-index")
    Boolean deleteIndex(@RequestParam("idx") String idx) throws IOException {
        final AcknowledgedResponse resp = restHighLevelClient.indices().delete(new DeleteIndexRequest(idx), RequestOptions.DEFAULT);
        return resp.isAcknowledged();
    }
}
