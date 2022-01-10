package cc.momas.sdr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sod-Momas
 * @since 2022/1/11
 */
//@SpringBootTest
public class RedisApplicationTests {
    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testRedisAdd() {
        final ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:8080/test/redis-add?key=aaa&value=xxx", String.class);
        Assertions.assertEquals(resp.getBody(), "true");
    }

    @Test
    public void testRedisGet() {
        final ResponseEntity<String> resp = restTemplate.getForEntity("http://localhost:8080/test/redis-get?key=aaa", String.class);
        Assertions.assertEquals(resp.getBody(), "xxx");
    }
}
