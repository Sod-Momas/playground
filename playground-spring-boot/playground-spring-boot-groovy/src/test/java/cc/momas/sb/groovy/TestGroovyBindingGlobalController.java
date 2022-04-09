package cc.momas.sb.groovy;

import groovy.lang.Binding;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 该类会把spring所有bean都注入进来给groovy，方便运行时取值
 *
 * @author Sod-Momas
 * @since 2022/4/9
 */
@RestController("testGroovyBindingGlobalController")
public class TestGroovyBindingGlobalController implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private final static String GROOVY_BINDING_NAME = "groovyBinding";
    @Autowired
    private TestService testService;

    @RequestMapping("test-global-groovy")
    public String testGlobalGroovy(@RequestParam Long id) {
        return testService.testQuery(id);
    }

    @Bean(GROOVY_BINDING_NAME)
    @ConditionalOnMissingBean
    public Binding groovyBinding() {
        Binding groovyBinding = new Binding();

        Map<String, Object> allBean = applicationContext.getBeansOfType(Object.class);
        for (Map.Entry<String, Object> entry : allBean.entrySet()) {
            if (GROOVY_BINDING_NAME.equals(entry.getKey())) {
                // 去除掉自身
                continue;
            }
            groovyBinding.setVariable(entry.getKey(), entry.getValue());
        }
        return groovyBinding;
    }
/*
    @Bean(GROOVY_BINDING_NAME)
    @ConditionalOnMissingBean
    public Binding groovyBinding1() {
        // 直接把spring所有Bean都注入到绑定关系中
        return new Binding(applicationContext.getBeansOfType(Object.class));
    }
*/


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
