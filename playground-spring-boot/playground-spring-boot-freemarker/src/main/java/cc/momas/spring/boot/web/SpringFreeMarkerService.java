package cc.momas.spring.boot.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;

/**
 *
 * @author Sod-Momas
 * @since 2022/4/8
 */
//@Component
public class SpringFreeMarkerService {
    @Autowired
    private Configuration config;

    public String parse(String template, Object model) throws IOException, TemplateException {
        Objects.requireNonNull(config, "freemarker configuration not found. please check current environment is NOT web environment.");
        final Template temp = config.getTemplate(template);
        StringWriter writer = new StringWriter();
        temp.process(model, writer);
        return writer.toString();
    }

}
