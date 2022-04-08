package cc.momas.spring.boot.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sod-Momas
 * @since 2022/4/8
 */
@Controller
public class FreeMarkerController {
    @ResponseBody
    @RequestMapping("/helloworld")
    public ModelAndView helloworld(@RequestParam("name") String name, ModelAndView mav) {
        mav.addObject("name", name);
        mav.setViewName("helloworld");
        return mav;
    }

    @RequestMapping("/fm/index")
    public String fmIndex(ModelMap modelMap) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "sod-momas");
        map.put("desc", "描述");
        // 添加属性 给模版
        modelMap.addAttribute("user", map);
        return "fm/index";
    }
}
