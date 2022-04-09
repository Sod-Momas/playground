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
        GroovyShell groovyShell = new GroovyShell(new Binding());

        String scriptContent =
                "import cc.momas.sb.groovy.TestService\n" +
                "def result = new TestService().testQuery(1L)\n" +
                "return result";

        Script script = groovyShell.parse(scriptContent);
        System.out.println(script.run());
    }
}
