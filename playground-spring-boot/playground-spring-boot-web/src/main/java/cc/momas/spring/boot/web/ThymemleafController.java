package cc.momas.spring.boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sod-Momas
 * @since 2023/6/24
 */
@Controller
public class ThymemleafController implements WebMvcConfigurer {

//    @RequestMapping({"/thymeleaf", "thymeleaf/index", "thymeleaf/index.html"})
//    String index() {
//        return "thymeleaf/index.html";
//    }

    @RequestMapping("thymeleaf/demos.html")
    ModelAndView objs(ModelAndView mav) {
        Map<String, Object> user = new HashMap<>();
        user.put("username", "Sod-Momas");
        user.put("age", 18);
        user.put("mobile", "10086");
        mav.addObject("user", user);
        mav.setViewName("thymeleaf/demos.html");
        return mav;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/thymeleaf").setViewName("thymeleaf/index.html");
        registry.addRedirectViewController("/thymeleaf/index", "/thymeleaf");
        registry.addRedirectViewController("/thymeleaf/index.html", "/thymeleaf");
        registry.addViewController("/thymeleaf/form.html").setViewName("thymeleaf/form.html");
//        registry.addViewController("/thymeleaf/demos.html").setViewName("thymeleaf/demos.html");
    }
}
