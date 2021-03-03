# how to install docker

该文描述如何安装并运行 docker 

# Ubuntu

```bash
# 安装依赖的命令
sudo apt-get install  apt-transport-https ca-certificates curl gnupg-agent software-properties-common
# 安装docker签名文件
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# 验证签名
sudo apt-key fingerprint 0EBFCD88
# 安装docker 稳定版仓库
sudo add-apt-repository \
    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
    $(lsb_release -cs) \
    stable"
# 更新apt仓库
sudo apt update
# 安装 docker 社区版
sudo apt-get install docker-ce docker-ce-cli containerd.io
# 启动docker服务
sudo service docker start
# 检查安装成功
docker -v
```

参考自: [Get Docker Engine - Community for Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/#install-using-the-repository)

# CentOS

```bash
# 安装依赖
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
# 安装仓库源
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 安装 docker 社区版引擎
sudo yum install docker-ce
# 如果当前用户不是 root, 运行这个可以把当前用户加入到 docker 用户组
sudo usermod -aG docker $(whoami)
# 设置开机自启动
sudo systemctl enable docker.service
# 启动 docker 服务
sudo systemctl start docker.service
# 检查安装成功
docker -v
```

参考自: [Get Docker Engine - Community for CentOS](https://docs.docker.com/install/linux/docker-ce/centos/#install-using-the-repository)

# Windows 

1. 访问 [Docker Identification](https://hub.docker.com/signup) 注册账号, 该页面使用了 google 人机验证, 所以请**保证可以访问google**
2. 访问 [Docker Desktop for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows) 点击右侧 Get Docker 下载安装程序
3. 打开下载的 `Docker for Windows Installer.exe` 进行安装
4. 点击桌面状态栏右下角小图标, 打开安装好的客户端, 进行登录
5. 在开始菜单按钮上点右键, 打开 `Windows PowerShell`, 即可开始使用 docker, 输入 `docker -v`, 如果显示相关版本号, 例如 `Docker version 19.03.2, build 6a30dfc`, 说明安装成功

# 建议

为了更好安装与使用 docker, 这里有几点建议.

1. 使用 Linux 系统进行安装使用
2. 如果不能使用 Linux, 可以尝试使用 Windows 10 提供的 Linux子系统, 参考 [Windows Subsystem for Linux Installation Guide for Windows 10](https://docs.microsoft.com/en-us/windows/wsl/install-win10)
3. docker 网站在国内访问很慢, 相信你注册的时候就感受到了, 所以建议科学上网完成注册下载, 然后在使用 docker 之前添加国内的镜像源, 让 docker 镜像下载速度飞起来
4. 保证 `2375` 端口是分配给 docker 的, 许多第三方插件会默认使用这个端口来连接 docker

# 参考资料

- [Get Docker Engine - Community for CentOS](https://docs.docker.com/install/linux/docker-ce/centos)
- [Get Docker Engine - Community for Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
- [Linux服务器,服务管理 - systemctl 命令详解](https://www.cnblogs.com/zdz8207/p/linux-systemctl.html)
- [Docker Desktop for Windows](https://hub.docker.com/editions/community/docker-ce-desktop-windows)
- [Windows Subsystem for Linux Installation Guide for Windows 10](https://docs.microsoft.com/en-us/windows/wsl/install-win10)

**Docker 更换国内源**

- [Dao Cloud](http://get.daocloud.io/)
- Dao Cloud [Docker 镜像站](https://www.daocloud.io/mirror)
- 中科大 [Docker 镜像使用帮助](https://lug.ustc.edu.cn/wiki/mirrors/help/docker)
- 简书 [Docker快速安装以及换镜像源 ](https://www.jianshu.com/p/34d3b4568059)