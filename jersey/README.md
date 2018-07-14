# Jersey

jersey是个实现了JAX-RS规范的RESTful框架

## 启动

1. 直接启动`cc.momas.jersey.Main`类
2. 使用maven命令启动
    
    ```bash
    mvn exec:java
    ```
    然后访问[http://localhost:8080/myapp/myresource](http://localhost:8080/myapp/myresource)可以得到响应
    ```output
    Got it!
    ```
    也可以访问[http://localhost:8080/myapp/application.wadl](http://localhost:8080/myapp/application.wadl)的webService接口
    
## 自定义

1. pom.xml
2. Main.class
3. packages

项目启动应该是启动了下面这个maven插件
```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>1.2.1</version>
    <executions>
        <execution>
            <goals>
                <goal>java</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <mainClass>cc.momas.jersey.Main</mainClass>
    </configuration>
</plugin>

```
它的`goal`就是启动命令的`exec:java`中的java  
然后`mainClass`指向启动的Main类，它是服务器的开始，包含一个main方法创建并创建一个内嵌的netty服务器

在Main方法里，有一行代码：
```java
final ResourceConfig rc = new ResourceConfig().packages("cc.momas.jersey");
```
packages(String);里面是资源的包名，大概是像spring注解扫描包那样的，写在这里就会去扫描里面的类和注解
