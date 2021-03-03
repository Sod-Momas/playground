package cc.momas.scp.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sod-Momas
 * @since 2021-01-31
 */
@RestController
public class TestController {
    @Autowired
    private Environment environment;

    @RequestMapping("config/{name}")
    public String config(@PathVariable("name") String name) {
        return environment.getProperty(name);
    }
}
