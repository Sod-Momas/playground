# 安装PostgreSQL

## 一键安装PostgreSQL

```bash
# 下载yum仓库扩展包
wget https://download.postgresql.org/pub/repos/yum/11/redhat/rhel-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm
# 安装扩展包
yum localinstall pgdg-redhat-repo-latest.noarch.rpm -y
# 安装postgreSql服务器
yum install postgresql-server -y
# 初始化服务器
postgresql-setup initdb
# 设置为开机自启动
systemctl enable postgresql.service
# 启动数据库服务器
systemctl start postgresql.service
```

## 详细步骤

1. 下载扩展仓库

    系统里面没有 PostgreSQL 的仓库, 所以需要到 [PostgreSQL Yum Repository - PostgreSQL  Yum 仓库扩展包下载页](https://yum.postgresql.org/repopackages.php) 下载   
    下载的命令是

    ```sh
    wget https://download.postgresql.org/pub/repos/yum/11/redhat/rhel-7-x86_64/pgdg-redhat-repo-latest.noarch.rpm
    ```

    下载完后包名为 `pgdg-redhat-repo-latest.noarch.rpm`

2. 安装扩展仓库到本地

    如果没有安装, yum会找不到PostgreSQL项目的地址, 造成无法安装的情况

    ```sh
    yum localinstall pgdg-redhat-repo-latest.noarch.rpm -y
    ```

3. 初始化服务器,并启动服务器

    如果你的系统是CentOS6, 则使用这个命令

    ```sh
    service postgresql initdb
    chkconfig postgresql on
    ```

    如果你的系统是CentOS7, 则使用这个命令

    ```sh
    postgresql-setup initdb
    systemctl enable postgresql.service
    systemctl start postgresql.service
    ```

## 连接到PostgreSQL

```sh
# 切换用户
su postgres
# 启动数据库客户端
psql
# 执行SQL
select version();
```

- PostgreSQL 仓库包下载页 [PostgreSQL Yum Repository](https://yum.postgresql.org/repopackages.php)
- PostgreSQL下载安装页 [Linux downloads (Red Hat family)](https://www.postgresql.org/download/linux/redhat/)
- 访问数据库 [1.4. Accessing a Database](https://www.postgresql.org/docs/11/tutorial-accessdb.html)