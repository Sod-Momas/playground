# JDBC

这个项目用来存放JDBC的基本用法 

## 启动前
在启动项目之前请保证本地装有对应的数据库

## MySql
- 默认端口为3306
- 默认用户名为`root`

## Microsoft SQL Server
- 默认端口为`1433`
- 默认用户名为`sa`
- 连接字符串写法 `jdbc:sqlserver://localhost;databaseName=test;integratedSecurity=true;`

获取数据库连接：
```java
class Jdbc{
    {
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://<server>;user=<user>;password=<password>;");
        System.out.println("Driver version: " + conn.getMetaData().getDriverVersion());
        
    }
}
```


分为两种连接模式 ：
- Windows集成身份验证
- 用户名密码验证

使用Windows集成身份验证的时候需要使用JNI调用dll验证，这个操作只能在Windows系统下实现，且区分x86架构与x64架构  

如果Ms sql没有用户名密码验证方式，请参考[这个](https://jingyan.baidu.com/article/6766299787b69c54d51b84bb.html)

驱动区分JRE版本，请选择对应的驱动，[maven仓库驱动列表](http://mvnrepository.com/artifact/com.microsoft.sqlserver/mssql-jdbc),
[官方驱动下载列表页](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-2017)

官方教程 ： [使用JDBC](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/using-the-jdbc-driver?view=sql-server-2017)

