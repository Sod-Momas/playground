# playground-spring-cloud-alibaba

spring cloud Alibaba 演示

## 端口分配

服务                             | 端口
--------------------------------|------
playground-sca-sakura           | 2301
playground-sca-kallen           | 2302

## 测试url

url                            | 结果和说明
------------------------------ |------
http://localhost:2302/hello    | `{"msg":"hello"}` 访问 playground-sca-kallen 项目的接口
http://localhost:2302/hello    | `{"msg":"hello"}` 访问 playground-sca-kallen 项目的接口

## 安装启动nacos集群

1. 下载nacos 
> 到github 的仓库release页下载 https://github.com/alibaba/nacos/releases  
> 这里下载了 1.4.0 版本 https://github.com/alibaba/nacos/releases/download/1.4.0/nacos-server-1.4.0.zip

2. 解压并复制三份，可以启动一个基本集群(由三个实例组成)
```shell
unzip nacos-server-1.4.0.zip
cd nacos-server-1.4.0
cp -r nacos nacos2200
cp -r nacos nacos2201
cp -r nacos nacos2202
```
3. 配置三个不同的集群启动地址，每个实例需要配置两个文件，分别是 `conf/application.properties`和`conf/cluster.conf`

**192.168.1.102**是我的本机ip，请使用的同学自己替换成自己的ip地址

配置 nacos2200 application.properties
```properties
#*************** Spring Boot Related Configurations ***************#
### Default web context path:
server.servlet.contextPath=/nacos
### Default web server port:
server.port=2200

#*************** Network Related Configurations ***************#
### If prefer hostname over ip for Nacos server addresses in cluster.conf:
# nacos.inetutils.prefer-hostname-over-ip=false

### Specify local server's IP:
nacos.inetutils.ip-address=192.168.1.102


#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://localhost:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=nacos
db.password=nacos
```
配置 nacos2200 cluster.conf
```text
192.168.1.102:2200
192.168.1.102:2201
192.168.1.102:2202
```

配置 nacos2201 application.properties
```properties
#*************** Spring Boot Related Configurations ***************#
### Default web context path:
server.servlet.contextPath=/nacos
### Default web server port:
server.port=2201

#*************** Network Related Configurations ***************#
### If prefer hostname over ip for Nacos server addresses in cluster.conf:
# nacos.inetutils.prefer-hostname-over-ip=false

### Specify local server's IP:
nacos.inetutils.ip-address=192.168.1.102


#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://localhost:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=nacos
db.password=nacos
```
配置 nacos2201 cluster.conf
```text
192.168.1.102:2200
192.168.1.102:2201
192.168.1.102:2202

```

配置 nacos2202 application.properties
```properties

#*************** Spring Boot Related Configurations ***************#
### Default web context path:
server.servlet.contextPath=/nacos
### Default web server port:
server.port=2202

#*************** Network Related Configurations ***************#
### If prefer hostname over ip for Nacos server addresses in cluster.conf:
# nacos.inetutils.prefer-hostname-over-ip=false

### Specify local server's IP:
nacos.inetutils.ip-address=192.168.1.102


#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
spring.datasource.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://localhost:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=nacos
db.password=nacos
```
配置 nacos2202 cluster.conf
```text
192.168.1.102:2200
192.168.1.102:2201
192.168.1.102:2202
```

4. 配置完成后就完成了nacos的配置了，但nacos的信息需要存储到MySQL里面所以需要再对MySQL进行配置

首先在MySQL创建nacos的数据库
```sql
CREATE DATABASE nacos;
USE nacos;
```

然后执行 `conf/nacos-mysql.sql` 里的SQL进行建表

建表完成后，需要再创建nacos的用户，用于nacos访问MySQL
```sql
-- 创建用户
CREATE USER nacos IDENTIFIED BY 'nacos';
-- 授权数据库表
GRANT ALL ON nacos.* TO nacos;
```

5. 以上都做完之后，就可以启动nacos集群啦

```shell
start nacos2200/bin/startup.cmd
start nacos2201/bin/startup.cmd
start nacos2202/bin/startup.cmd
```