# Nacos

## 一键安装

```bash
wget https://github.com/alibaba/nacos/releases/download/1.4.1/nacos-server-1.4.1.zip
unzip nacos-server-1.4.1.zip
cd nacos
bin/startup.cmd -m standalone
```

## 分步下载安装

1. 到release页下载nacos https://github.com/alibaba/nacos/releases
2. 下载nacos 1.4.1 版本
```
wget https://github.com/alibaba/nacos/releases/download/1.4.1/nacos-server-1.4.1.zip
```
3. 解压nacos
```
unzip nacos-server-1.4.1.zip
```
4. 在开发机器上使用独立运行模式启动(默认使用集群模式启动)
```
bin/startup.cmd -m standalone
```
5. 关闭nacos
```
bin/shutdown.cmd
```

## 参考资料

- [Nacos 官方网站](https://nacos.io/zh-cn/index.html)
- [Nacos 快速开始](https://nacos.io/zh-cn/docs/quick-start.html) 介绍了如何安装启动