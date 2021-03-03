package cc.momas.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sod-Momas
 * @since 2019.09.10
 */
@RestController
public class HelloWorldController {
    @RequestMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "hello " + name;
    }
}
