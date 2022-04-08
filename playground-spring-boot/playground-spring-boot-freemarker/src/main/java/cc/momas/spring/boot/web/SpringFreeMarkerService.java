package cc.momas.spring.boot.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;

/**
 * @author Sod-Momas
 * @since 2022/4/8
 */
@Component
public class SpringFreeMarkerService {
    @Autowired
    private FreeMarkerConfigurationFactoryBean configuration;

    public String parse(String template, Object model) throws IOException, TemplateException {
        final Configuration config = configuration.getObject();
        Objects.requireNonNull(config, "freemarker configuration not found.");
        final Template temp = config.getTemplate(template);
        StringWriter writer = new StringWriter();
        temp.process(model, writer);
        return writer.toString();
    }

}
