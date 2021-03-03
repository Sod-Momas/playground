package cc.momas.apache;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * freemarker示例
 *
 * @author Sod-Momas
 * @since 2021-03-02
 */
public class FreemarkerApplication {
    public static void main(String[] args) {
        // configuration 初始化配置
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        try {
            // 配置模板文件根目录
            URL path = FreemarkerApplication.class.getClassLoader().getResource("templates");
            File dir = new File(Objects.requireNonNull(path).toURI());
            System.out.println("current template root:" + dir.getAbsolutePath());
            cfg.setDirectoryForTemplateLoading(dir);
            // 配置编码
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }

        // get data
        Map<String, Object> root = new HashMap<>();
        root.put("user", "Big Joe");
        Product latest = new Product();
        latest.setUrl("products/greenmouse.html");
        latest.setName("green mouse");
        root.put("latestProduct", latest);

        try (Writer out = new OutputStreamWriter(System.out)) {
            // get template
            Template temp = cfg.getTemplate("hello.ftl");

            // merge template and data
            temp.process(root, out);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}
