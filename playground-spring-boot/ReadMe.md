# playground-spring-boot
Spring Boot 演示项目


## 加载配置文件

代码：

主类
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@SpringBootApplication
public class LearnSpringBootApplication implements CommandLineRunner {

    // 使用javaBean接收配置, 然后在这里注入
    @Autowired
    private TestConfigurationProperties properties;
    // 直接使用 @Value 注入配置
    @Value("${testName}")
    private String name;

    // 注入日期类型的时候需要额外配置
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    @Value(value = "${date}")
    private LocalDateTime date;
    // @Value 可以使用SpEL做运算
    @Value("#{T(java.lang.Math).random()%2==0 ? 'yes':'no'}")
    private String test;

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringBootApplication.class);
    }

    @Override
    public void run(String... args) {
        System.out.println("java version: "+System.getProperty("java.version"));
        System.out.println(date);
        System.out.println(test);
        System.out.println(name);
        System.out.println(properties.getTestName());
    }
}
```

配置类

```java
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// @Configuration 指定该类是个配置类
@Configuration
// 引入classpath下的配置文件, 算是引入外部文件
@PropertySource("classpath:test.properties")
// 引入系统指定目录下的配置文件, 算是引入外部文件
//@PropertySource("file:c:/var/test.properties")
// 指定前缀, 如果引入的是yml文件，此前缀会失效，导致此前缀下的所有配置为null
@ConfigurationProperties(prefix = "test")
class TestConfigurationProperties { 

    // 加去加载配置文件中的 test.testName 属性
    private String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "TestConfigurationProperties{" +
                "testName='" + testName + '\'' +
                '}';
    }
}
```

配置文件

application.properties

```properties
date=2019-12-01 23:08:19
testName=this is test name
```

test.properties

```properties
test.testName=the test name from properties
```

test.yml

```yml
test:
  testName: test name from yml
```