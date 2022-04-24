package cc.momas.sb.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Sod-Momas
 * @since 2022/4/24
 */
@Service
public class CaffeineCacheService implements CacheService {
    private final Cache<String, Object> cache = Caffeine.<String, Object>newBuilder()
//            .softValues()
//            .weakKeys()
            .weakValues()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10)
            .build();


    @Override
    public Object get(String key) {
        if (key == null) return null;
        return cache.getIfPresent(key);
    }

    @Override
    public void set(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void delete(String key) {
        cache.invalidate(key);
    }

    @Override
    public void deleteAll() {
        cache.invalidateAll();
    }
}
