# how to use docker

该文档阐述了如何启动 docker 镜像与镜像的简单管理,

环境:  
OS 名称: Microsoft Windows 10 Pro  
OS 版本: 10.0.18362 暂缺 Build 18362  
Docker version 19.03.2, build 6a30dfc  

本篇已经脱离了 java 相关领域, 是纯粹的 docker 教程

## 查看已有镜像

```bash
PS C:\Users\sod> docker images
REPOSITORY                           TAG       IMAGE ID       CREATED              SIZE
cc.momas/playground-docker-spotify   latest    a89ce26432a0   About a minute ago   479MB
alpine/git                           latest    04dbb58d2cea   4 weeks ago          25.1MB
openjdk                              jre       6b23d41384f9   23 months ago        479MB
```

## 启动容器

使用 `REPOSITORY` 来启动

```bash
PS C:\Users\sod> docker run --name pds --detach --publish 80:80 cc.momas/playground-docker-spotify
aaf76162dc6395a33129acef8cc59fe1351e8a99bcb5e7a5ac38bd0f8379c53f
```

也可以使用 `IMAGE ID` 来启动

```bash
PS C:\Users\sod> docker run -d -p 80:80 eb6877a2d000
762894b681ecd0dca8f033070da6fe7e7f07a664d6cf3280d5eea8f1bf029702
```

启动容器之后就可以访问容器了, 这个不同的项目有不同的方法, 示例项目里是提供了 http 接口, 可以使用 `curl` 来访问

```ps1
PS C:\Users\sod> curl http://localhost/?name=sod


StatusCode        : 200
StatusDescription :
Content           : hello sod
RawContent        : HTTP/1.1 200
                    Keep-Alive: timeout=60
                    Connection: keep-alive
                    Content-Length: 9
                    Content-Type: text/plain;charset=UTF-8
                    Date: Mon, 22 Feb 2021 16:37:54 GMT

                    hello sod
Forms             : {}
Headers           : {[Keep-Alive, timeout=60], [Connection, keep-alive], [Content-Length, 9], [Content-Type, text/plain;charset=UTF-8]...}
Images            : {}
InputFields       : {}
Links             : {}
ParsedHtml        : mshtml.HTMLDocumentClass
RawContentLength  : 9

```

或者直接使用浏览器访问 http://localhost/?name=docker, 会显示

```output
hello docker
```

## 查看容器

查看正在运行的容器

```bash
PS C:\Users\sod> docker ps
CONTAINER ID   IMAGE                                COMMAND                  CREATED          STATUS          PORTS                NAMES
fc6503e44ba3   cc.momas/playground-docker-spotify   "java -jar playgroun…"   41 seconds ago   Up 40 seconds   0.0.0.0:80->80/tcp   pds
```

## 停止容器

使用 `CONTAINER ID` 来停止容器

```bash
PS C:\> docker stop fc6503e44ba3
fc6503e44ba3
```

这个时候再查看正在运行的容器就没有东西了

```bash
PS C:\Users\sod> docker ps
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
```

不过可以查已经停止的容器

```bash
PS C:\Users\sod>  docker ps -a
CONTAINER ID   IMAGE                                COMMAND                  CREATED              STATUS                        PORTS     NAMES
fc6503e44ba3   cc.momas/playground-docker-spotify   "java -jar playgroun…"   About a minute ago   Exited (143) 20 seconds ago             pds
```

> 如果你在运行容器的时候, 没有加 `-d` 参数, 容器直接在控制台运行, 然后又使用  
> `Ctrl` + `C` 去停止容器, 可能会造成 docker 服务异常, 这个时候重启一下 docker  
> 就好了.  

## 删除容器

使用 `CONTAINER ID` 来删除容器, 值得注意的是, **删除容器之前容器要处于停止状态, 否则会失败**

```bash
PS C:\> docker rm fc6503e44ba3
fc6503e44ba3
```

有个方便的命令可以一次性删除已经停止的容器 (正在运行的容器不受影响)

```bash
PS C:\> docker container prune
WARNING! This will remove all stopped containers.
Are you sure you want to continue? [y/N] y
Deleted Containers:
ccb26c527d5ba1ef68b3773b7dcfdeee91c46d910928a68f60b40f4194c57ee4

Total reclaimed space: 0B
```

## 删除镜像

使用 `IMAGE ID` 来删除镜像, 值得注意的是, **删除镜像之前必须停止所有基于该镜像的所有容器, 删除删除所有基于该镜像的所有容器, 否则删除镜像失败**

```bash
PS C:\> docker rmi ad1255a85bff
Untagged: cc.momas.sod/docker-maven-plugin:latest
Deleted: sha256:ad1255a85bfff9cf963fdbb608422d2e2892d5de8edc42584fe8ad235fbeae68
Deleted: sha256:bd30333fd7783ace2c4b4de18ffbf2510481be6f69c471b22c2501cd04a2ce0a
Deleted: sha256:0694ff0a27805369c85694447e608b49cd333f3d6be77b89fc3196796a28a35f
```

这个时候查看镜像, 可以看到列表已经没有删除的那个镜像了

```bash
PS C:\> docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
openjdk             8                   08ded5f856cc        3 weeks ago         488MB
openjdk             jre                 6b23d41384f9        5 months ago        479MB
```

## 参考资料

- [docker启动,重启,关闭命令](https://blog.csdn.net/EasternUnbeaten/article/details/80463837)
- [Docker容器的创建、启动、和停止](https://www.cnblogs.com/linjiqin/p/8608975.html)
- [Docker删除容器与镜像](https://blog.csdn.net/qq_32447301/article/details/79387649)
