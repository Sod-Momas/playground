# Struts2 

## 简介

> 这个项目是Struts2的一个demo，使用maven搭建，在在build完成之后访问[localhost](http://localhost:80)访问项目
> 本项目使用了两种开发方式：
>  1. `cc.momas.java.demo.web.action.LoginAction`使用了配置文件配置的方式开发  
>  2. `cc.momas.java.demo.web.action.IndexAction`使用了注解方式开发（未实现）
>  

## 启动
本项目使用maven管理，直接使用maven运行即可。  
在项目根目录打开命令行，运行
```shell
mvn clean tomcat7:run
```
项目会启动并监听80端口，在浏览器访问`localhost:80`即可访问

## 配置

Struts2有几个地方需要配置，一个是`/WEB-INF/web.xml`,需要配置使Struts2生效

```xml
<web-app>

<filter>
	<description>struts2过滤器</description>
	<filter-name>struts2</filter-name>
	<filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
</filter>

<filter-mapping>
	<filter-name>struts2</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>

</web-app>
```

然后是在classpath目录下，也就是maven项目里的src/main/resources/目录下写配置文件`struts.xml`,这里给一个简单的示例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
	<constant name="struts.devMode" value="true"></constant>

	<package name="default" extends="struts-default">
		<action name="login" class="cc.momas.java.demo.web.action.LoginAction">
			<result>
				index.jsp
			</result>
		</action>
	</package>
</struts>
```
## 参考 
参考连接：
1. [使用Maven配置Struts2](http://blog.csdn.net/shuiguolan/article/details/51728127)
2. [Log4j2 could not find a logging implementation](https://blog.csdn.net/qq_40233736/article/details/79281454)
