# playground-sql-mssql

java 连接mssql(MicroSoft SQL Server) 的示例

**使用之前强烈建议先看一遍文档**

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

## 参考资料

- [第 1 课：创建和查询数据库对象](https://docs.microsoft.com/zh-cn/sql/t-sql/lesson-1-creating-database-objects?view=sql-server-ver15)
- [下载 Microsoft SQL Server JDBC 驱动程序](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15&viewFallbackFrom=sql-server-2017--)
- [连接 URL 示例](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/connection-url-sample?view=sql-server-ver15)
- [使用 JDBC 驱动程序](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/using-the-jdbc-driver?view=sql-server-ver15) 官方教程
- [JDBC 驱动程序的系统要求](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/system-requirements-for-the-jdbc-driver?view=sql-server-ver15)