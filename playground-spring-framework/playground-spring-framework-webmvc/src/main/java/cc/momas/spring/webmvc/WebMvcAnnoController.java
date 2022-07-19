package cc.momas.spring.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Sod-Momas
 * @since 2022/7/19
 */
@Controller
@ResponseBody
public class WebMvcAnnoController {
    {
        System.out.println("load " + getClass());
    }

    @RequestMapping("hello")
    public String hello(String name) {
        return "hello " + name;
    }
}
