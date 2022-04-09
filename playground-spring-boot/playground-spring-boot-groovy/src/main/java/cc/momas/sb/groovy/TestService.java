package cc.momas.sb.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

/**
 * @author Sod-Momas
 * @since 2022/4/9
 */
public class TestService {

    public String testQuery(long id) {
        return "Test query success, id is " + id;
    }
}
