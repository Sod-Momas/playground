# 使用 Docker 安装运行 Oracle 数据库

1. 拉取 oracle 数据库镜像

```powershell
C:\> docker pull registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```

2. 创建本地数据目录，这样就可以把数据库的数据都放到 docker 外，不怕容器重启丢失数据了

```powershell
C:\> mkdir /oracle_data/app
C:\> mkdir /oracle_data/dpdump
C:\> mkdir /oracle_data/oraInventory
```

3. 启动 docker 镜像

```powershell
C:\> docker run -d -p 1521:1521 --name oracle_11g -v /oracle_data/app:/opt/oracle/app -v /oracle_data/dpdump:/opt/oracle/dpdump -v /oracle_data/oraInventory:/opt/oracle/oraInventory -e ORACLE_HOME=/home/oracle/app/oracle/product/11.2.0/dbhome_2 -e ORACLE_SID=helowin registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
46e0f84c03ed9cce9f6166a0d26ffe8de73ede5dce0a14f282344405afd6591e
```

4. 进入容器，执行命令

```powershell
C:\> docker exec -it oracle_11g bash
```

5. 切换用户并登录 SQL Plus， `root` 用户的密码是 `helowin`

```shell
[oracle@46e0f84c03ed /]$ su root
Password:
[root@46e0f84c03ed /]# ln -s $ORACLE_HOME/bin/sqlplus /usr/bin
[root@46e0f84c03ed /]# su - oracle
[oracle@46e0f84c03ed ~]$ sqlplus /nolog

SQL*Plus: Release 11.2.0.1.0 Production on Sat Jul 3 15:28:37 2021

Copyright (c) 1982, 2009, Oracle.  All rights reserved.

SQL>
```

6. 修改 `sys`和`system` 用户的密码，改为 `oracle`

```sqlplus
SQL>
SQL> conn / as sysdba
Connected.
SQL>
SQL> ALTER USER SYSTEM IDENTIFIED BY oracle;

User altered.

SQL>
SQL> ALTER USER SYS IDENTIFIED BY oracle;

User altered.

SQL>
SQL> ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;

Profile altered.

SQL> exit
Disconnected from Oracle Database 11g Enterprise Edition Release 11.2.0.1.0 - 64bit Production
With the Partitioning, OLAP, Data Mining and Real Application Testing options
[oracle@46e0f84c03ed bin]$
```

7. 使用客户端工具连接数据库

|    key | value     |
| -----: | --------- |
| 用户名 | sys       |
|   密码 | oracle    |
|   主机 | localhost |
|   端口 | 1521      |
| 服务名 | helowin   |

## 参考资料

- [docker 安装 Oracle 数据库](https://blog.csdn.net/qq_21137441/article/details/89339257)
