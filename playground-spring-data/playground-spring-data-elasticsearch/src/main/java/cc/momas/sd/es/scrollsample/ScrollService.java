package cc.momas.sd.es.scrollsample;

import java.io.IOException;
import java.util.Map;

/**
 * Scroll Api 示例
 *
 * @author Sod-Momas
 * @since 2022/3/19
 */
public interface ScrollService {
    /**
     * 查询游标
     *
     * @param scrollId 游标id，第一次查询时传null
     * @param fields   要查询的字段，传null表示查询全部字段
     * @param size     游标每页数据量
     * @param indeces  索引名，不传就查所有索引
     * @return 查到的数据, key有两个： scrollId(String) 和 hits(org.elasticsearch.search.SearchHits)
     */
    Map<String, Object> queryScroll(String scrollId, String[] fields, int size, String[] indeces);

    /**
     * 查询游标下一页
     *
     * @param scrollId 游标id，第一次查询时传null
     * @return 查到的数据, key有两个： scrollId(String) 和 hits(org.elasticsearch.search.SearchHits)
     */
    Map<String, Object> queryScrollNextPage(String scrollId) throws IOException;

    /**
     * 查询游标第一页
     *
     * @param fields 要查询的字段，传null表示查询全部字段
     * @param size   游标每页数据量
     * @return 查到的数据, key有两个： scrollId(String) 和 hits(org.elasticsearch.search.SearchHits)
     */
    Map<String, Object> queryScrollFirstPage(int size, String[] fields, String[] indices) throws IOException;

    /**
     * 清理游标
     *
     * @param scrollId 游标id
     * @throws IOException elasticsearch异常时
     */
    void clearScroll(String scrollId) throws IOException;
}
