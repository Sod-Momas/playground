package freemarker;

import freemarker.template.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TemplateUtil {

    private static Configuration cfg;

    public static void init(String templateFolder) {
        File portalTemplateFolder = new File(templateFolder);
        cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        try {
            cfg.setDirectoryForTemplateLoading(portalTemplateFolder);
        } catch (IOException e) {
        }
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
    }

    public static void process(String templateName, Map<String, Object> dataMap, OutputStream out) {
        if (dataMap == null) {
            dataMap = new HashMap<String, Object>();
        }
        try {
            Template temp = cfg.getTemplate(templateName, "UTF-8");
            Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
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
            Writer writer = new OutputStreamWriter(bos, StandardCharsets.UTF_8);
            temp.process(dataMap, writer);
            return bos.toByteArray();
        } catch (Exception e) {
        }
        return new byte[0];
    }

    public static String getAsString(String templateName, Object value) {
        SimpleHash root = new SimpleHash((ObjectWrapper) null);
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