package cc.momas.apache.cli;

import org.apache.commons.cli.*;

import java.util.Arrays;

/**
 * Apache Common CLI 项目示例，例如想要使用 port参数(缩写 p )输入参数值80，则可以使用以下所有方式执行
 * <pre>
 * PS C:\Users\sod\Downloads\git-dir\playground-java\playground-apache-common\apache-cli> java -jar .\target\apache-cli-1.0.jar -p=80
 * [-p=80]
 * port=80
 * PS C:\Users\sod\Downloads\git-dir\playground-java\playground-apache-common\apache-cli> java -jar .\target\apache-cli-1.0.jar --p=80
 * [--p=80]
 * port=80
 * PS C:\Users\sod\Downloads\git-dir\playground-java\playground-apache-common\apache-cli> java -jar .\target\apache-cli-1.0.jar -port=80
 * [-port=80]
 * port=80
 * PS C:\Users\sod\Downloads\git-dir\playground-java\playground-apache-common\apache-cli> java -jar .\target\apache-cli-1.0.jar --port=80
 * [--port=80]
 * port=80
 * </pre>
 *
 * @author Sod-Momas
 * @since 2021-02-12
 */
public class CliApplication {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        revole(args);
    }

    private static void revole(String[] args) {
        // create Options object
        Options options = new Options();
        // add t option
        options.addOption("p", "port", true, "The port of server bind.");

        CommandLineParser parser = new DefaultParser();

        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            // get value from cmd
            final Object port = cmd.getParsedOptionValue("p");
            System.out.println("port=" + port);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
