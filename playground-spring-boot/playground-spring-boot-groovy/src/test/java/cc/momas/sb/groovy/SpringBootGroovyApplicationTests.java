package cc.momas.sb.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Sod-Momas
 * @since 2022/4/9
 */
@SpringBootTest
public class SpringBootGroovyApplicationTests {
    @Autowired
    private Binding binding;
    @Autowired
    private TestGroovyBindingController testGroovyBindingController;

    @Test
    public void testGroovyGlobalController() {
        String script = "def result = testGroovyBindingGlobalController.testGlobalGroovy(3L);\n return result;";
        // 这个绑定关系已经在初始化spring 容器的时候把所有bean注入进来了，所以可以直接使用spring里的bean
        final GroovyShell shell = new GroovyShell(binding);
        // 每次parse是有成本的，所以建议把script存储起来，避免每次都parse
        final Script parse = shell.parse(script);
        final Object result = parse.run();
        System.out.println(result);
    }

    @Test
    public void testGroovyBindingController(){
        String script = "def a =1; def b=2; return a+b;";
        final String result = testGroovyBindingController.execute(script);
        System.out.println(result);
    }
}
