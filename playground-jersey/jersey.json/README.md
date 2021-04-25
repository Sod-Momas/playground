# jersey.json

### 简介

用jersey开发的 RESTful 服务器,  

这个项目用于展示如何统一规范输出 json 或 xml 数据

### 开发步骤

1. 在 jersey.boot 的基础上添加依赖

```xml
<dependency>
	<groupId>org.glassfish.jersey.media</groupId>
	<artifactId>jersey-media-json-jackson</artifactId>
	<version>2.25</version>
</dependency>
```

2. 新建统一规范类

```java 
public class HttpResponse {

	/**
	 * 响应状态, 最好使用http 状态码标明,默认成功
	 */
	public int status = 200;
	/**
	 * 数据起始 偏移量,多用于分页标记这一页第一项的id或者下标
	 */
	public int start = 0;
	/**
	 * 数据量, 多用于分页,标记一页多少行数据
	 */
	public int count = 10;
	/**
	 * 数据,数据
	 */
	public Object data;
	/**
	 * 响应的消息,可以告诉前端发生了什么异常
	 */
	public String msg;

	// 这里可以添加更多的工厂方法,生产成功的响应与失败的响应
}

```

为了示例 xml 格式数据,还多写了一个类,这个类需要使用注解标明它会被格式化成xml  
> 现在基本没人用 xml 啦...

```java

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlResponse extends HttpResponse {
}


```

3. 记得修改`web.xml`里的servlet,

添加 jersey的Servlet `org.glassfish.jersey.servlet.ServletContainer`,  
并且添加启动参数`jersey.config.server.provider.packages`指定资源(控制器)包名为`jersey.json.resources`

改完如下:

```xml
<servlet>
	<servlet-name>JerseyServletContainer</servlet-name>
	<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
	<init-param>
		<param-name>jersey.config.server.provider.packages</param-name>
		<param-value>jersey.json</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
	<servlet-name>JerseyServletContainer</servlet-name>
	<url-pattern>/webapi/*</url-pattern>
</servlet-mapping>
```

4. 项目访问地址:

访问的地址按照以下规则拼接:
	
	1. 服务器指定的域名和端口
	2. `web.xml`中servlet的url-pattern
	3. 资源类中的`@Path`注解的值
	4. 资源类的方法的`@Path`注解的值 

例如:在本地启动这个项目

- 本地的域名为 `localhost`
- pom.xml 中指定启动端口为`8080`
- web.xml 中指定url-pattern拦截为 `/webapi/*`
- JsonResponse 的@Path 注解路径为 `json`
- JsonResponse 的@Path 注解路径为`{id}` (使用{}包起来的路径为可变路径,可以为任意值,使用这种写法的方法会再使用@PathParam注解作为参数给方法传值)
- 所以访问路径为 `http://localhost:8080/webapi/json/id`,就可以访问到JsonResponse里的那个方法



### 启动步骤

如果需要直接从代码启动,可以使用 maven 插件 `tomcat7-maven-plugin` 来启动项目

1. 在项目根目录下运行 `mvn clean tomcat7:run` 
	> 其中 `mvn` 是启动 项目管理软件 maven , `clean` 是启动 maven 清理资源生命周期方法,可以忽略,但建议加上, `tomcat7:run` 是启动tomcat7插件并以run目标方式启动
2. 启动后会显示`信息: Starting ProtocolHandler ["http-bio-8080"]  ` 之类的文字,这个时候打开浏览器,访问 `http://localhost:8080 ` 就可以访问到项目的`Hello World` 资源了(因为 @Path 注解指定了 Hello World 类的路径是 `/` )

### 参考资料

-  [Jersey 开发RESTful（七）Jersey快速入门](https://www.jianshu.com/p/88f97b90963c)