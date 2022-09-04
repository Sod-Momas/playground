package cc.momas.spring.boot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sod-Momas
 * @since 2022/9/3
 */
@RestController
public class HelloController {
    @RequestMapping("hello")
    String hello(String name) {
        return "hello " + name;
    }
}
