# 安装Redis

## 一键安装

```sh
# 更新仓库
sudo apt-get update
# 安装Redis 
sudo apt-get install redis-server
# 启动Redis服务
sudo service redis-server start
```

安装完成之后测试是否成功

```output
$ redis-cli
127.0.0.1:6379> ping
PONG
```

## 参考资料

- [Ubuntu安装配置Redis](https://www.jianshu.com/p/c8723b677304)