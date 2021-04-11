# playground-webjars

webjars 工具

maven 坐标
```xml
<!-- https://mvnrepository.com/artifact/org.webjars/bootstrap -->
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>3.3.7</version>
</dependency>
```
script 地址为
```html
<!--         /webjars/{artifactId}/{version}/{filename} -->
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
```

使用maven启动项目（使用Ctrl+C来停止）

```shell
mvn tomcat7:run
```

## 测试url

url  | 用处
---|---
http://localhost/ | 测试页面

## 参考资料

- [WebJars](https://www.webjars.org/) 官网
- [Instructions for Servlet 3](https://www.webjars.org/documentation#servlet3) servlet3使用webjars
- [WebJars介绍及使用](https://blog.csdn.net/q_0718/article/details/80105318)