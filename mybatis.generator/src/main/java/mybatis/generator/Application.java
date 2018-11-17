package mybatis.generator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 启动类
 */
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final String CONFIG_FILE = "generatorConfig-mysql.xml";

    public static void main(String[] args) {
        try {
            gen();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (XMLParserException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


    private static void gen() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
//		File configFile = new File("generatorConfig.xml");
        InputStream configFile = Application.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        info(config);
    }

    // 打印配置信息
    private static void info(Configuration config) {
        Context context = config.getContext("mysql");
        SqlMapGeneratorConfiguration sqlmapConfig = context.getSqlMapGeneratorConfiguration();
        System.out.println(sqlmapConfig.getTargetProject());
        JavaClientGeneratorConfiguration javaClientConfig = context.getJavaClientGeneratorConfiguration();
        System.out.println(javaClientConfig.getTargetProject());
        JavaModelGeneratorConfiguration javaModelConfig = context.getJavaModelGeneratorConfiguration();
        System.out.println(javaModelConfig.getTargetProject());

    }

}
