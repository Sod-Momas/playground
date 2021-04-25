# jersey.boot

### 简介

用jersey开发的 RESTful 服务器,这是一个最简单的示例项目

### 开发步骤

1. 添加依赖

```xml
<dependency>
	<groupId>org.glassfish.jersey.containers</groupId>
	<artifactId>jersey-container-servlet</artifactId>
	<version>2.25</version>
</dependency>
```

2. 新建一个资源类

```java
// 包类很重要,用来扫描资源
package jersey.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Path 注解指定了访问路径
@Path("/")
public class HelloWorld {
	
	// GET 注解指定了http方法,要用get方法访问
	// Produces 指定了返回了数据类型,会添加到 http 响应头里
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMsg() {
		// 这里可以写业务逻辑,但为了演示方便,返回固定字符串
		return "Hello World";
	}
}

```

3. 在 web.xml 里添加配置,整个 xml 如下

```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	version="2.5">

	<servlet>
		<servlet-name>JerseyServletContainer</servlet-name>
		<!-- jersey 的核心Servlet -->
		<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
		<init-param>
			<!-- 指定资源类在哪个包下的参数 -->
			<param-name>jersey.config.server.provider.packages</param-name>
			<!-- 指定了 jersey.resouces 是资源类的包名 -->
			<param-value>jersey.resources</param-value>
		</init-param>
		<!-- 在启动服务器的时候,这个 servlet 要第 1 个启动 -->
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>JerseyServletContainer</servlet-name>
		<!-- 指定拦截的url格式 -->
		<url-pattern>/webapi/*</url-pattern>
	</servlet-mapping>
</web-app>

```

4. 代码到这里就写完了,可以打包成 war 放到 servelt窗口中运行了.

### 启动步骤

如果需要直接从代码启动,可以使用 maven 插件 `tomcat7-maven-plugin` 来启动项目

1. 在项目根目录下运行 `mvn clean tomcat7:run` 
	> 其中 `mvn` 是启动 项目管理软件 maven , `clean` 是启动 maven 清理资源生命周期方法,可以忽略,但建议加上, `tomcat7:run` 是启动tomcat7插件并以run目标方式启动
2. 启动后会显示`信息: Starting ProtocolHandler ["http-bio-8080"]  ` 之类的文字,这个时候打开浏览器,访问 `http://localhost:8080 ` 就可以访问到项目的`Hello World` 资源了(因为 @Path 注解指定了 Hello World 类的路径是 `/` )

### 参考资料

-  [Jersey 开发RESTful（七）Jersey快速入门](https://www.jianshu.com/p/88f97b90963c)