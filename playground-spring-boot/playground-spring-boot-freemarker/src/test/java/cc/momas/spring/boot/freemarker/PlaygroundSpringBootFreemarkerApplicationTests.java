package cc.momas.spring.boot.freemarker;

import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * @author Sod-Momas
 * @since 2022/4/8
 */
@SpringBootTest
public class PlaygroundSpringBootFreemarkerApplicationTests {
    private final static Logger log = LoggerFactory.getLogger(PlaygroundSpringBootFreemarkerApplicationTests.class);

    @Autowired
    private SpringFreeMarkerService springFreeMarkerService;

    @Test
    public void parseTest() throws TemplateException, IOException {
        Map<String, String> param = Collections.singletonMap("name", "world");
        final String result = springFreeMarkerService.parse("helloworld.ftlh", param);
        log.info("result={}", result);
    }
}
