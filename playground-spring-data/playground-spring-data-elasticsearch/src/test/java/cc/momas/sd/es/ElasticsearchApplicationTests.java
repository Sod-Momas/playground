package cc.momas.sd.es;

import cc.momas.sd.es.sample1.KibanaSampleDataEcommerce;
import cc.momas.sd.es.sample1.KibanaSampleDataEcommerceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * @author Sod-Momas
 * @since 2022/3/6
 */
@SpringBootTest
public class ElasticsearchApplicationTests {
    @Autowired
    private KibanaSampleDataEcommerceRepository sampleDataEcommerceRepository;
    @Autowired
    private ObjectMapper objectMapper;


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
}
