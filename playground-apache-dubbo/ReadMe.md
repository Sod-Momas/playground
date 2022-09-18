# playground-dubbo

Spring Boot Dubbo 演示

## 端口配置

| 服务                        | 端口        |
|---------------------------|-----------|
| playground-dubbo-provider | 2200      |
| playground-dubbo-consumer | 2201,2202 |

## 测试url

- http://localhost:2202/acc/login?loginName=sod&loginPwd=P@ssw0rd 正常会返回 {"loginName":"sod","loginPwd":null,"birthday":"2021-02-05T01:18:07.8443658","weight":123454321.6789,"bloodType":"B"}

## docker-compose

1. 根据docker-compose.yml 启动服务
```bash
# 在 zookeeper-replices-docker-compose.yml 文件目录下执行该命令， -d 表示后台启动
docker-compose -f .\zookeeper-replices-docker-compose.yml up -d
```
2. `docker-compose config` 查看配置
3. `docker exec -it <容器id> bash` 交互式进入容器
4. `bin/zkServer.sh status` 查看zookeeper集群状态（follower/leader）

## 参考资料

- [Apache Dubbo Spring Boot Project](https://github.com/apache/dubbo-spring-boot-project)
- [搭建zookeeper集群](https://www.cnblogs.com/yloved/p/11881128.html) 使用docker-compose.yml搭建本地  zk集群用于dubbo测试
- [springboot使用dubbo和zookeeper](https://www.cnblogs.com/yloved/p/11881151.html)
- [nacos 实战](https://www.cnblogs.com/crazymakercircle/p/14231815.html)
- [Dubbo使用Nacos注册中心](https://blog.csdn.net/u011618818/article/details/106077989)