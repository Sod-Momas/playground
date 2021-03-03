package cc.momas.gw.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sod-Momas
 * @since 2021-01-30
 */
@RestController
public class HiController {
    @Autowired
    private HiService hiService;

    @RequestMapping("/hi")
    public Object hi(@RequestParam(value = "name", required = false) String name) {
            return hiService.hi(name);
    }
}
