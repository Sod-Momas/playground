# Struts2 demo
> 这个项目是Struts2的一个demo，使用maven搭建，在在build完成之后访问[localhost](http://localhost:80)访问项目
> 本项目使用了两种开发方式：
>  1. `cc.momas.java.demo.web.action.LoginAction`使用了配置文件配置的方式开发  
>  2. `cc.momas.java.demo.web.action.IndexAction`使用了注解方式开发（未实现）
>  

Struts2有几个地方需要配置，一个是`web.xml`,需要配置使Struts2生效

```
<filter>
	<description>struts2过滤器</description>
	<filter-name>struts2</filter-name>
	<filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>struts2</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
然后是在classpath目录下，也就是maven项目里的src/main/resources/目录下写配置文件`struts.xml`,至于配置文件怎么写，就是struts2的配置知识了，另说，本demo不介绍太多
本demo里用到的配置如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
	<constant name="struts.devMode" value="true"></constant>
	<constant name="struts.i18n.encoding" value="UTF-8"></constant>
	<constant name="struts.locale" value="zh_CN"></constant>

	<package name="default" extends="struts-default">
		<action name="login" class="cc.momas.java.demo.web.action.LoginAction">
			<result>
				index.jsp
			</result>
		</action>
	</package>
</struts>
```
另外的两个log4j开关的文件是日志工具的配置文件，具体请找一下LOG4J的知识，这里不多讲

参考连接：

http://blog.csdn.net/shuiguolan/article/details/51728127
