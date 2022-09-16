package cc.momas.spring.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author Sod-Momas
 * @see <a href="https://docs.spring.io/spring-shell/docs/2.1.1/site/reference/htmlsingle/#using-spring-shell-your-first-command">2.3. 你的第一个命令</a>
 * @since 2022/9/16
 */
@ShellComponent
public class TwoSumCommand {
    /**
     * <pre>
     *     shell:>add --a 1 --b 2
     * </pre>
     */
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        return a + b;
    }
}
