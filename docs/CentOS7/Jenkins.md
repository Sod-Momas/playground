# 安装Jenkins

## 一键安装

- 方法1

    ```sh
    wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
    sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
    yum install jenkins -y
    ```

- 方法2 (要求CentOS7)

    ```sh
    # 安装 jdk 8
    sudo yum install -y java-1.8.0-openjdk-devel
    curl --silent --location http://pkg.jenkins-ci.org/redhat-stable/jenkins.repo | sudo tee /etc/yum.repos.d/jenkins.repo
    sudo rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key
    # 启动 Jenkins
    sudo systemctl start jenkins
    # 查看 Jenkins 启动状态
    systemctl status jenkins
    # 将 Jenkins 添加到开机自动启动列表
    sudo systemctl enable jenkins
    # 打印初始化密码
    sudo cat /var/lib/jenkins/secrets/initialAdminPassword
    ```

- 方法3 (要求 Docker )

    ```sh
    docker run \
    --name="jenkins" \
    --hostname="jenkins" \
    --restart always \
    -u root \
    -d \
    -p 80:8080 \
    -p 50000:50000 \
    -v /data/jenkins:/var/jenkins_home \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v /etc/timezone:/etc/timezone \
    -v /etc/localtime:/etc/localtime \
    jenkinsci/blueocean
    ```

安装完成后需要初步解锁配置才能使用, 第一次启动需要的时候比较长. 如果您的服务器是使用 Nginx或其他 web 服务器做反向代理, 那么需要做些额外配置, 请参考文末的参考链接

## 常用命令

查找Jenkins安装路径:

```sh
rpm -ql jenkins
```

修改Jenkins配置

```sh
vim /etc/sysconfig/jenkins
```

查看Jenkins初次解密密码

```sh
cat /var/lib/jenkins/secrets/initialAdminPassword
```

查看Jenkins运行日志

```sh
tail  /var/log/jenkins
```

查看端口占用情况

```sh
netstat -ntlp
```

- 如果启动后一直运行不起来, 请检查一下用户组有没有jenkins用户和用户组.
- 有些时候使用jenkins普通用户是无法运行的, 网上大多解决方案都是改用root用户运行
另外的解决方案(**未测试**)

```sh
vim /etc/sysconfig/jenkins
# make sure that $JENKINS_USER="jenkins"

chown -R jenkins:jenkins /var/lib/jenkins
chown -R jenkins:jenkins /var/cache/jenkins
chown -R jenkins:jenkins /var/log/jenkins
systemctl restart jenkins
```

## 参考资料

- [RedHat Linux RPM packages for Jenkins](https://pkg.jenkins.io/redhat-stable/)
- [Installing Jenkins on Red Hat distributions](https://wiki.jenkins.io/display/JENKINS/Installing+Jenkins+on+Red+Hat+distributions)
- [阿里云上如何利用yum安装jenkins](https://www.cnblogs.com/cnhkzyy/p/9281522.html)
- [一台新服务器如何安装及配置Jenkins](https://juejin.im/post/5b93ba7f5188255c8c0c79a8) `/etc/sysconfig/jenkins` 是配置文件
- 配置Nginx反向代理jenkins [Jenkins behind an NGinX reverse proxy](https://wiki.jenkins.io/display/JENKINS/Jenkins+behind+an+NGinX+reverse+proxy)
- [How To Install Jenkins on CentOS 7](https://linuxize.com/post/how-to-install-jenkins-on-centos-7)
- [jenkins 端口修改](https://blog.csdn.net/T748588330/article/details/79917424)
- [Jenkins active (exited)](https://stackoverflow.com/questions/42607771/jenkins-active-exited)
- [How To Use Systemctl to Manage Systemd Services and Units](https://www.digitalocean.com/community/tutorials/how-to-use-systemctl-to-manage-systemd-services-and-units)