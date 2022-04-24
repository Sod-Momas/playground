package cc.momas.sb.caffeine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Sod-Momas
 * @since 2022/1/23
 */
@SpringBootTest
public class SpringBootCaffeineApplicationTests {

    @Autowired
    private CacheService cacheService;

    @Test
    public void loadContext() {
        System.out.println("hello test");
    }

    @Test
    public void testCache() {
        String key = "_foo";
        String value = "bar";

        cacheService.set(key, value);
        String result = (String) cacheService.get(key);
        Assertions.assertEquals(value, result);

        cacheService.delete(key);
        result = (String) cacheService.get(key);
        Assertions.assertNull(result);

        cacheService.deleteAll();
        result = (String) cacheService.get(key);
        Assertions.assertNull(result);
    }
}
