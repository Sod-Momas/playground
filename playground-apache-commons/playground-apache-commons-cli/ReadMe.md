# playground-apache-commons-cli

Apache Commons CLI 项目使用示例

## 使用方法

使用 `org.apache.commons.cli.CommandLineParser` 解析 main 方法的 String[] 参数，
转换为 `org.apache.commons.cli.CommandLine` 对象，
然后使用 `getParsedOptionValue` 方法获取对应的参数值。

例如想要使用 `p` 参数输入参数值 `80`，
可以输入 `-p80`,`-p=80`,`--p=80` 等形式，
完成命令为  `java -jar app.jar -p=80`

```java
import org.apache.commons.cli.*;

void revole(String[] args) {
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
```

## 参考资料

- [commons-cli jar包的使用](http://www.voidcn.com/article/p-usfprpns-mv.html) 简单讲解使用方法
- [Usage Scenarios](http://commons.apache.org/proper/commons-cli/usage.html) 官方的使用教程