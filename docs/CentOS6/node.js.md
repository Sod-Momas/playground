# Node.js安装

## 一键安装

```sh
curl --silent --location https://rpm.nodesource.com/setup_10.x | sudo bash -
sudo yum install gcc-c++ make
sudo yum -y install nodejs  
```

## Ubuntu apt 一键安装

```bash
sudo apt install nodejs npm
nodejs --version
```

## 压缩包下载安装

1. 安装 Node.js 环境
    > Node.js 是运行在服务端的 JavaScript, 是基于 Chrome JavaScript V8 引擎建立的平台。
1. 下载并安装 Node.js
    1. 下载最新的稳定版 v6.10.3 到本地  
        `wget https://nodejs.org/dist/v6.10.3/node-v6.10.3-linux-x64.tar.xz`
    1. 下载完成后, 将其解压  
        `tar xvJf node-v6.10.3-linux-x64.tar.xz`  
    1. 将解压的 Node.js 目录移动到 /usr/local 目录下  
        `mv node-v6.10.3-linux-x64 /usr/local/node-v6`  
    1. 配置 node 软链接到 /bin 目录  
        `ln -s /usr/local/node-v6/bin/node /bin/node`  
1. 配置 npm  
    > npm 是 Node.js 的包管理和分发工具。它可以让 Node.js 开发者能够更加轻松的共享代码和共用代码片段
    - 下载 node 的压缩包中已经包含了 npm , 我们只需要将其软链接到 bin 目录下即可  
        `ln -s /usr/local/node-v6/bin/npm /bin/npm`
1. 配置环境变量
    1. 将 /usr/local/node-v6/bin 目录添加到 $PATH 环境变量中可以方便地使用通过 npm 全局安装的第三方工具
        `echo 'export PATH=/usr/local/node-v6/bin:$PATH' >> /etc/profile`
    2. 生效环境变量  
        `source /etc/profile`  
1. 使用 npm  
    - 通过 npm 安装进程管理模块 forever  
    `npm install forever -g`

## 参考资料

- [enterprise-linux-and-fedora](https://nodejs.org/en/download/package-manager/#enterprise-linux-and-fedora)
- [How to install Node.js and npm on Ubuntu 18.04](https://linuxize.com/post/how-to-install-node-js-on-ubuntu-18.04/)