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

## Spring Boot 引入配置文件的几个方式

主类

```java
@EnableDay03
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```

配置类

```java
public class ApplicationEntry {
    static {
        System.out.println("run");
    }
}
```
```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(Day20191203AutoConfiguration.class)
public @interface EnableDay03 {
}

```
```java
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "learn.spring")
public class Day20191203Properties {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
```
```java

import cc.momas.demo.ApplicationEntry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ApplicationEntry.class})
@EnableConfigurationProperties({Day20191203Properties.class})
public class Day20191203AutoConfiguration {
    static {
        System.out.println("day 03 auto config");
    }
}

```

配置文件

META-INF/spring.factories
```properties
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  cc.momas.autoconfig.Day20191203AutoConfiguration
```

> 运行 cc.momas.third_party_project.Main ,控制台打印 `day 03 auto config` 表示自动配置成功

1. 责任链设计模式实现
2. 抽象配置成一个接口, 让需要配置参数的类依赖接口而非实现 (里氏替换原则)
3. spring 的 spring.factories 的 SPI 机制
4. 写个注解作为标记,可以命名为 EnableXXX, 然后在这个注解里加个组合注解, @Import 进来 AutoConfiguration 类
5. spring boot 把 dispatcherServlet 放到 spring 容器, spring mvc 是把 spring 容器放到 dispatcherServlet 里
6. 如果存在 org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport 实现类, 则 spring mvc 自动配置会失效
7. spring boot 会在当前容器里找到所有 HttpMessageConverter 放进 spring mvc 消息转换器列表里. 所以spring boot 加 HttpMessageConverter 的时候加个 @Bean 就可以, 而不用去自己写 spring mvc 配置类
8. spring boot 会在当前容器里找到所有 ContentNegotiatingViewResolver 自动注入到 spring mvc 消息解析器列表里
   9.通过实现 org.springframework.web.servlet.ViewResolver 接口来实现一个 ViewResolver , spring boot会自动添加到 spring mvc 里

主要类:

- DispatcherServlet
- WebAutoConfiguration
- ContentNegotiatingViewResolver
- Converter
- HttpMessageConverters
- ApplicationContextAware

