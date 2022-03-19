package cc.momas.sd.es.scrollsample;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sod-Momas
 * @since 2022/3/19
 */
@Service
public class ScrollServiceImpl implements ScrollService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private final Logger log = LoggerFactory.getLogger(ScrollServiceImpl.class);


    @Override
    public Map<String, Object> queryScroll(String scrollId, String[] fields, int size, String[] indeces) {
        try {
            return StringUtils.isBlank(scrollId)
                    ? queryScrollFirstPage(size, fields, indeces)
                    : queryScrollNextPage(scrollId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * scroll api 查询
     *
     * @param scrollId 游标id
     * @return 下一页数据
     * @throws IOException ElasticSearch查询异常时
     */
    @Override
    public Map<String, Object> queryScrollNextPage(String scrollId) throws IOException {
        //在这里获取id去查下一页；
        SearchScrollRequest req = new SearchScrollRequest(scrollId);
        //指定scrollId的生存时间
        req.scroll(TimeValue.timeValueMinutes(1L));
        //执行查询获取返回结果
        SearchResponse resp = restHighLevelClient.scroll(req, RequestOptions.DEFAULT);
        scrollId = resp.getScrollId();
        final SearchHits hits = resp.getHits();

        Map<String, Object> map = new HashMap<>();
        map.put("scrollId", scrollId);
        map.put("hits", hits);
        return map;
    }

    /**
     * scroll api 查询
     *
     * @param size    一页数据量
     * @param fields  需要查询的字段,不传则查询全部
     * @param indices 需要查询的索引,不传则查询全部索引
     * @return 下一页数据
     * @throws IOException ElasticSearch查询异常时
     */
    @Override
    public Map<String, Object> queryScrollFirstPage(int size, String[] fields, String[] indices) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(size);

        final SearchSourceBuilder source = searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        if (fields != null && 0 < fields.length) {
            source.fetchSource(fields, new String[0]);
        }

        SearchRequest req = (indices == null || indices.length == 0)
                ? new SearchRequest("*")
                : new SearchRequest(indices);
        // 指定scrollId的生存时间
        req.scroll(TimeValue.timeValueMinutes(1L));
        req.source(source);
        SearchResponse resp = restHighLevelClient.search(req, RequestOptions.DEFAULT);
        final String scrollId = resp.getScrollId();
        final SearchHits hits = resp.getHits();

        Map<String, Object> map = new HashMap<>();
        map.put("scrollId", scrollId);
        map.put("hits", hits);
        return map;
    }

    @Override
    public void clearScroll(String scrollId) throws IOException {
        ClearScrollRequest req = new ClearScrollRequest();
        req.addScrollId(scrollId);
        ClearScrollResponse resp = restHighLevelClient.clearScroll(req, RequestOptions.DEFAULT);
        if (resp.isSucceeded()) {
            log.info("清除scroll成功");
        } else {
            log.info("清除scroll失败");
        }
    }
}
