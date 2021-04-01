package cc.momas.sca.nacos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Sod-Momas
 * @since 2021-04-01
 */
@RestController
public class EchoController {
    @RequestMapping("/hello")
    public Object hello(){
        return Collections.singletonMap("msg", "hello");
    }
}
