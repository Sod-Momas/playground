package cc.momas.sb.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.junit.jupiter.api.Test;

/**
 * @author Sod-Momas
 * @since 2022/4/9
 */
public class StaticTests {
    @Test
    public void runScriptTest() {
        // 直接运行一段groovy脚本
        GroovyShell groovyShell = new GroovyShell(new Binding());

        String scriptContent = "import cc.momas.sb.groovy.TestService\n" +
                "def result = new TestService().testQuery(1L)\n" +
                "return result";

        Script script = groovyShell.parse(scriptContent);
        System.out.println(script.run());
    }

    @Test
    public void runScriptBindTest() {
        final Binding binding = new Binding();
        // 通过GroovyShell预设对象
        binding.setProperty("testService", new TestService());

        GroovyShell groovyShell = new GroovyShell(binding);

        String scriptContent = "def result = testService.testQuery(2L); return result;";

        Script script = groovyShell.parse(scriptContent);
        System.out.println(script.run());
    }
}
