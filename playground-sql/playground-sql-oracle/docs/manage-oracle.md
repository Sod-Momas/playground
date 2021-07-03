# 管理 Oracle


## 登录 sql/plus
```shell
[oracle@46e0f84c03ed bin]$ sqlplus /nolog

SQL*Plus: Release 11.2.0.1.0 Production on Sat Jul 3 17:00:10 2021

Copyright (c) 1982, 2009, Oracle.  All rights reserved.

SQL> conn / as sysdba
Connected.
SQL>
SQL>
```

## 登录成功之后，关闭数据库服务
```sqlplus
SQL> shutdown immediate
Database closed.
Database dismounted.
ORACLE instance shut down.
SQL>
```
## 登录成功之后，开启数据库服务
```sqlplus
SQL> startup
ORACLE instance started.

Total System Global Area 1603411968 bytes
Fixed Size                  2213776 bytes
Variable Size             402655344 bytes
Database Buffers         1191182336 bytes
Redo Buffers                7360512 bytes
Database mounted.
Database opened.
SQL>
```

## 停止监听

```shell
[oracle@46e0f84c03ed bin]$ $ORACLE_HOME/bin/lsnrctl stop

LSNRCTL for Linux: Version 11.2.0.1.0 - Production on 03-JUL-2021 17:03:43

Copyright (c) 1991, 2009, Oracle.  All rights reserved.

Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=IPC)(KEY=EXTPROC1521)))
The command completed successfully
[oracle@46e0f84c03ed bin]$
```

## 启动监听
```shell
[oracle@46e0f84c03ed bin]$ $ORACLE_HOME/bin/lsnrctl start

LSNRCTL for Linux: Version 11.2.0.1.0 - Production on 03-JUL-2021 17:04:35

Copyright (c) 1991, 2009, Oracle.  All rights reserved.

Starting /home/oracle/app/oracle/product/11.2.0/dbhome_2/bin/tnslsnr: please wait...

TNSLSNR for Linux: Version 11.2.0.1.0 - Production
System parameter file is /home/oracle/app/oracle/product/11.2.0/dbhome_2/network/admin/listener.ora
Log messages written to /home/oracle/app/oracle/diag/tnslsnr/46e0f84c03ed/listener/alert/log.xml
Listening on: (DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(KEY=EXTPROC1521)))
Listening on: (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=46e0f84c03ed)(PORT=1521)))

Connecting to (DESCRIPTION=(ADDRESS=(PROTOCOL=IPC)(KEY=EXTPROC1521)))
STATUS of the LISTENER
------------------------
Alias                     LISTENER
Version                   TNSLSNR for Linux: Version 11.2.0.1.0 - Production
Start Date                03-JUL-2021 17:04:35
Uptime                    0 days 0 hr. 0 min. 0 sec
Trace Level               off
Security                  ON: Local OS Authentication
SNMP                      OFF
Listener Parameter File   /home/oracle/app/oracle/product/11.2.0/dbhome_2/network/admin/listener.ora
Listener Log File         /home/oracle/app/oracle/diag/tnslsnr/46e0f84c03ed/listener/alert/log.xml
Listening Endpoints Summary...
  (DESCRIPTION=(ADDRESS=(PROTOCOL=ipc)(KEY=EXTPROC1521)))
  (DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=46e0f84c03ed)(PORT=1521)))
The listener supports no services
The command completed successfully
[oracle@46e0f84c03ed bin]$
```
