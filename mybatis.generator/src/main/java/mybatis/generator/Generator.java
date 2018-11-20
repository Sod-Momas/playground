package mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 启动类
 *
 * @author Sod-Momas
 */
public class Generator {

    private static final Logger log = LoggerFactory.getLogger(Generator.class);
    private static final String CONFIG_FILE = "generatorConfig-mysql.xml";

    public static void main(String[] args) {
        try {
            gen();
        } catch (IOException | XMLParserException | InvalidConfigurationException | SQLException | InterruptedException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }


    private static void gen() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream configFile = Generator.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        log.info("generate successfully");
    }

}
