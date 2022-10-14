package cc.momas.sd.es;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.StatusLine;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 用于检测索引与创建mapping的工具
 *
 * @author Sod-Momas
 * @since 2022/10/14
 */
public class ESMappingUtil {
    private static final Logger log = LoggerFactory.getLogger(ESMappingUtil.class);

    /**
     * 判断指定的索引名是否存在
     *
     * @param indexName 索引名
     * @return 存在：true; 不存在：false;
     */
    public static boolean isExistsIndex(RestHighLevelClient client, String... indexName) {
        try {
            return client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("", e);
            return false;
        }
    }

    public static boolean createIfNoExist(RestHighLevelClient client, String index, String settings) {
        if (isExistsIndex(client, index)) {
            log.debug("index：{} 存在。", index);
            return false;
        }
        synchronized (ESMappingUtil.class) {
            // 并发双校验
            if (isExistsIndex(client, index)) {
                log.debug("index：{} 存在。", index);
                return true;
            }
            log.warn("index 不存在: {}", index);
            RestClient restClient = client.getLowLevelClient();
            if (StringUtils.isBlank(settings)) {
                log.error("{}mapping 模板不存在！", index);
                return true;
            }
            try {
                Request request = new Request("PUT", "/" + index);
                request.setJsonEntity(settings);
                Response response = restClient.performRequest(request);
                log.info("{}mapping创建完成。", settings);
                if (log.isDebugEnabled()) {
                    log.debug("response:{}", response);
                }
                return response.getStatusLine().getStatusCode() == 200;
            } catch (Exception e) {
                log.error("{}mapping创建失败:{}", settings, e.getMessage());
                return false;
            }
        }
    }
}