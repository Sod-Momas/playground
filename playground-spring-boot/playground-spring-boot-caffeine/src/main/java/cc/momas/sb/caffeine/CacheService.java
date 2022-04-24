package cc.momas.sb.caffeine;

/**
 * @author Sod-Momas
 * @since 2022/4/24
 */
public interface CacheService {
    /**
     * 添加缓存
     *
     * @param key 缓存key
     * @return 缓存值
     */
    Object get(String key);

    /**
     * 设置缓存
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    void set(String key, Object value);

    /**
     * 删除缓存
     *
     * @param key 缓存key
     */
    void delete(String key);

    /**
     * 删除所有缓存
     */
    void deleteAll();
}
