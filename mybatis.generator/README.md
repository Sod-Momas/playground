## mybatis.generator

这个项目用于展示如何使用mybatis代码生成器

或者说用来保存一些常规操作写法/配置

## 项目启动

直接启动 `mybatis.generator.Application` 类

## JDBC 连接

#### MySql
- 默认端口为3306
- 默认用户名为`root`
- 连接字符串写法`jdbc:mysql://localhost/test`

#### Microsoft SQL Server
- 默认端口为`1433`
- 默认用户名为`sa`
- 连接字符串写法 `jdbc:sqlserver://localhost;databaseName=test;integratedSecurity=true;`


## 参考资料

- [mybatis generator](https://github.com/mybatis/generator.git)
- [Running MyBatis Generator With Java](http://www.mybatis.org/generator/running/runningWithJava.html)
- [使用 JDBC 驱动程序(Microsoft)](https://docs.microsoft.com/zh-cn/sql/connect/jdbc/using-the-jdbc-driver?view=sql-server-2017)
- [Connecting to MySQL Using the JDBC DriverManager Interface](https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html)
