# MySQL安装

## 一键安装脚本

> 安装完成之后会打印首次登录的**临时密码**,如果不明白请看后面的分步演示

1. 安装数据库:

    ```bash
    wget https://dev.mysql.com/get/mysql80-community-release-el7-1.noarch.rpm
    yum localinstall mysql80-community-release-el7-1.noarch.rpm 
    yum install -y mysql-community-server
    ```

2. 启动mysqld服务

    ```sh
    # 查看服务状态
    service mysqld status
    service mysqld start
    grep 'temporary password' /var/log/mysqld.log
    ```

3. 修改登录密码

    ```sh
    # 输入临时密码登录
    mysql -u root -p
    ```

    执行sql

    ```sql
    # MyNewPass4! 为新密码
    ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
    ```

## 分步安装

1. 先安装mysql的yum存储库

    > 这一步好像也可以不做,直接跳过可能也可以,但官方教程里这样写,建议安装上去

    ```sh
    # 下载rpm包
    wget https://dev.mysql.com/get/mysql80-community-release-el7-1.noarch.rpm

    # 用yum安装到本地
    yum localinstall mysql80-community-release-el7-1.noarch.rpm 
    ```

    查看启用的是哪个版本的仓库(截止至2018年8月25日,是8.0版本)  

    ```sh
    yum repolist enabled | grep "mysql.*-community.*"
    ```

    如果需要安装其他版本的mysql,请使用如下命令:

    ```sh
    sudo yum-config-manager --disable mysql80-community
    sudo yum-config-manager --enable mysql57-community
    ```

2. 安装mysql社区版

    > 这将安装MySQL server(mysql-community-server)的包以及运行服务器所需组件的包  
    > 包括client (mysql-community-client)的包  
    > 客户端和服务器的常见错误消息和字符集(mysql-community-common)  
    > 以及共享客户端库(mysql-community-libs)

    ```sh
    # 安装mysql社区版
    sudo yum install -y mysql-community-server
    ```

3. 启动MySQL服务

    > yum安装完成会自动启动,这一步不需要任何操作

    查看mysql运行状态

    ```sh
    systemctl status mysqld.service
    ```

4. 使用默认密码登录 MySQL

    1. 查看临时密码

        ```sh
        grep 'temporary password' /var/log/mysqld.log
        ```

        会输出大概以下语句

        ```output
        2018-08-25T01:22:50.998655Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: 36Jiek%JezsI
        ```

        这里的密码就是 `36Jiek%JezsI`

    2. 登录mysql,输入密码

        ```sh
        mysql -u root -p
        ```

    3. 修改root用户的数据库登录密码

        ```sql
        ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
        ```

5. 安装完成,现在可以执行后续的正常操作了,连接数据库,执行SQL等.

## 删除MySQL

1. yum卸载

```sh
yum remove mysqld -y
```

1. 删除文件

```sh
rm -rf /var/lib/mysql
rm -rf /etc/selinux/targeted/active/modules/100/mysql
rm -rf /usr/lib64/mysql
```

## 参考 

- [2.5.1 Installing MySQL on Linux Using the MySQL Yum Repository](https://dev.mysql.com/doc/refman/8.0/en/linux-installation-yum-repo.html)
- [mysql80-community-release-el7-1.noarch.rpm](https://dev.mysql.com/get/mysql80-community-release-el7-1.noarch.rpm)
- [Linux下yum安装MySQL yum安装MySQL指定版本](https://blog.csdn.net/Jerome_s/article/details/52883234)