package freemarker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;

public class TemplateUtil {


	private static Configuration cfg;

	public static void init(String templateFolder){
		File portalTemplateFolder = new File(templateFolder);
		cfg = new Configuration();
		try {
			cfg.setDirectoryForTemplateLoading(portalTemplateFolder);
		} catch (IOException e) {
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	public static void process(String templateName, Map<String, Object> dataMap, OutputStream out) {
		if (dataMap == null){
			dataMap = new HashMap<String, Object>();
		}
		try {
			Template temp = cfg.getTemplate(templateName, "UTF-8");
			Writer writer = new OutputStreamWriter(out, "UTF-8");
			temp.process(dataMap, writer);
		} catch (Exception e) {
		}
	}

	public static byte[] getAsBytes(String templateName, Map<String, Object> dataMap) {
		if (dataMap == null) {
			dataMap = new HashMap<String, Object>();
		}

		try {
			Template temp = cfg.getTemplate(templateName, "UTF-8");

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			Writer writer = new OutputStreamWriter(bos, "UTF-8");
			temp.process(dataMap, writer);
			return bos.toByteArray();
		} catch (Exception e) {
		}
		return new byte[0];
	}

	public static String getAsString(String templateName, Object value) {
		SimpleHash root = new SimpleHash();
		root.put("object", value);

		try {
			Template temp = cfg.getTemplate(templateName, "UTF-8");

			StringWriter writer = new StringWriter();
			temp.process(root, writer);
			return writer.toString();
		} catch (Exception e) {
		}
		return "";
	}
}