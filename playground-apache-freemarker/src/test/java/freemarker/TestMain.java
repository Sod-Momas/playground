package freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

public class TestMain {

    public static void main(String[] args) throws Exception {
        new TestMain().test1();
    }

    public void test1() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        URL path = TestMain.class.getClassLoader().getResource("templates");
        File dir = new File(Objects.requireNonNull(path).toURI());
        System.out.println(dir.getAbsoluteFile());
        cfg.setDirectoryForTemplateLoading(dir);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        var root = new HashMap<>();
        root.put("user", "Big Joe");
        var latest = new HashMap<>();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");

        Template temp = cfg.getTemplate("test.ftl");

        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }
}
