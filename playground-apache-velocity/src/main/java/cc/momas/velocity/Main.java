package cc.momas.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * @author Sod-Momas
 * @since 2019.07.29
 **/
public class Main {

    public static void main(String[] args) throws IOException {

        /*
         * 初始化
         */
        // 指定资源加载方式为 文件
        Velocity.setProperty("resource.loaders", "file");
        // 指定资源目录为 src/main/resources/
//        Velocity.setProperty("file.resource.loader.path", "/");
        Velocity.setProperty("file.resource.loader.path", "src/main/resources/");
        // 指定加载资源的加载器
        Velocity.setProperty("resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 可选的步骤, 初始化Velocity引擎
        Velocity.init();

        /*
         * 检查模板文件是否存在
         */

        String existsFilePath = "folder/template.js.vm";
        boolean exists = Velocity.resourceExists(existsFilePath);
        if (!exists) {
            System.out.println(existsFilePath + " dose not exists");
            return;
        }
        System.out.println(existsFilePath);

        /*
         * 获取模板文件进进行合成
         */

        // 获取变量上下文, 用来替换模板里面的占位符
        Context context = new VelocityContext();
        context.put("foo", "bar");

        // 获取模板文件, 如果获取不到会报错
        Template template = Velocity.getTemplate(existsFilePath);
        // 获取要写入的输出流, 这里为了演示直接输出到控制台
//        Writer target = Files.newBufferedWriter(Paths.get("target/destinct.file"), StandardCharsets.UTF_8);
        Writer targetFileWriter = new OutputStreamWriter(System.out);

        template.merge(context, targetFileWriter);
        // 这一步很重要, 把输出缓存刷到硬盘, 如果不执行则产生空文件,内容会丢失
        targetFileWriter.flush();
        targetFileWriter.close();
    }
}
