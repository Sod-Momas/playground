# playground-javaee-servlet

## 项目介绍
`cc.momas.jee.servlet.exceptionprocess `,一个使用Filter做统一异常处理的web应用 (demo)

## 软件架构

1. 使用JRE 11 或更高版本
2. 使用Tomcat7 或更高版本(测试使用tomcat7)
3. 使用Eclipse Photon (4.8.0)开发

请求通过 Filter->Servlet->Filter

如果在Servlet处发生异常,服务器会抛500错误,为了统一解决这个问题,使用Filter做异常捕获,把异常留在可控制范围内,做出相应处理并返回一个正确的响应给前端


## 安装教程

1. 直接使用Eclipse导入该Web项目
2. 如果出现错误,可以自己新建一个web项目项目,然后把源代码复制过去(java代码和html)

## 使用说明

1. `mvn tomcat7:run`
2. 访问 http://localhost/exception/
3. 测试异常是否有被捕获，http 状态为 200 表明统一过滤器有好好工作


