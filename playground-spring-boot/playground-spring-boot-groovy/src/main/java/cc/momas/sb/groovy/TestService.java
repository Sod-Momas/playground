package cc.momas.sb.groovy;

import org.springframework.stereotype.Service;

/**
 * @author Sod-Momas
 * @since 2022/4/9
 */
@Service
public class TestService {

    public String testQuery(long id) {
        return "Test query success, id is " + id;
    }
}
