# playground-docker-dockerfile
演示如何使用 Dockerfile 打包一个 maven 工程

## Dockerfile 解析

构建镜像命令请参考 `build.sh`

```dockerfile
# 基于maven镜像，环境选择jdk11
FROM maven:3-jdk-11-slim
# 进入 /app目录，不存在会创建
WORKDIR /app
ADD pom.xml pom.xml
ADD src src
# 打包程序并重命名为 app.jar
RUN mvn package && mv target/*.jar ./app.jar
# 当容器启动时运行该指令
ENTRYPOINT ["java","-cp","app.jar","cc.momas.docker.dockerfile.DockerfleApplication"]
```

## 运行容器

首先确认镜像已经打包好
```shell
PS C:\> docker images
REPOSITORY                                             TAG           IMAGE ID       CREATED        SIZE
cc.momas/playground-docker-dockerfile                  1.2           0467a3b7c39a   2 hours ago    793MB
maven                                                  latest        1bfb542d4598   15 hours ago   786MB
```
可以看到镜像 id 为 0467a3b7c39a ，启动该镜像
```shell
PS C:\> docker run 0467a3b7c39a
hello world
java version: 17.0.1
app name: playground-docker-dockerfile
```
至此演示完毕