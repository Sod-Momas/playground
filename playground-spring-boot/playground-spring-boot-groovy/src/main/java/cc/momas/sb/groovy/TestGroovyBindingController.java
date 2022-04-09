package cc.momas.sb.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * 仅在本类内实例化一个groovy编译器
 *
 * @author Sod-Momas
 * @since 2022/4/9
 */
@RestController("testGroovyBindingController")
public class TestGroovyBindingController {
    @Autowired
    private Binding groovyBinding;
    private GroovyShell groovyShell;

    @PostConstruct
    public void init() {
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setSourceEncoding(StandardCharsets.UTF_8.displayName());

        GroovyClassLoader classLoader = new GroovyClassLoader(this.getClass().getClassLoader());
        groovyShell = new GroovyShell(classLoader, groovyBinding, cc);
    }

    @RequestMapping(value = "/execute-groovy")
    public String execute(@RequestBody String scriptContent) {
        Script script = groovyShell.parse(scriptContent);
        return String.valueOf(script.run());
    }
}
