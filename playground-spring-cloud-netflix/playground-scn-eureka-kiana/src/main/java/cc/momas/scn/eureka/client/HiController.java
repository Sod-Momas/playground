package cc.momas.scn.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author Sod-Momas
 * @since 2021-01-30
 */
@RestController
public class HiController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/hi")
    public Object hi(@RequestParam("name") String name) {
        final String msg = name == null ? "this is kiana" : (name + " from " + serverPort);
        return Collections.singletonMap("msg", msg);
    }
}
