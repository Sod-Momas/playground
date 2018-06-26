package cc.momas.demo.java.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TestMain {

	public static void main(String[] args) {
		// configuration
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
		try {URL path = TestMain.class.getClassLoader().getResource("templates");
			File dir = new File(path.toURI());
			cfg.setDirectoryForTemplateLoading(dir);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
		} catch (Exception e1) {
			e1.printStackTrace();
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
			Template temp = cfg.getTemplate("hello.ftlh");
			
			// merge template and data
			temp.process(root, out);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		} 
		
	}
}


