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

    @Test
    public void testGroovyGlobalController() {
        String script= "def result = testGroovyBindingGlobalController.testGlobalGroovy(3L);\n return result;";
        // 这个绑定关系已经在初始化spring 容器的时候把所有bean注入进来了，所以可以直接使用spring里的bean
        final GroovyShell shell = new GroovyShell(binding);
        final Script parse = shell.parse(script);
        final Object result = parse.run();
        System.out.println(result);
    }

}
