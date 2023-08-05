# how to build image in maven

该文章阐述了如何使用 com.spotify/docker-maven-plugin 把项目打包成 docker 镜像

## 本项目如何打包成 docker 镜像

执行之前请检查

1. docker 已经在您的机器上正常运行
2. maven 可以在您当前控制台使用

执行以下命令

```bash
mvn package
```

出现以下文字的时候表示打包到本地仓库成功了
```
...
[INFO] Building image cc.momas/playground-docker-spotify
Step 1/3 : FROM openjdk:jre

 ---> 6b23d41384f9
Step 2/3 : ADD /playground-docker-spotify-1.0.jar //

 ---> ef4c4b389af1
Step 3/3 : ENTRYPOINT ["java", "-jar", "playground-docker-spotify-1.0.jar"]

 ---> Running in 5278ba4119f5
Removing intermediate container 5278ba4119f5
 ---> eb6877a2d000
ProgressMessage{id=null, status=null, stream=null, error=null, progress=null, progressDetail=null}
Successfully built eb6877a2d000
Successfully tagged cc.momas/playground-docker-spotify:latest
[INFO] Built cc.momas/playground-docker-spotify
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
...
```

> spring-boot 插件会先把项目打包, 然后 docker 插件再把 spring-boot 插件打包好的 jar 打包到 docker 镜像里, 过程会下载镜像, 需要一段时间

执行后在 PowerShell 里执行 `docker images` 就可以看到镜像了, 示例中 `IMAGE ID` 为 `ad1255a85bff` 就是打包出来的镜像 

```PowerShell
PS C:\> docker images
REPOSITORY                         TAG                 IMAGE ID            CREATED             SIZE
cc.momas.sod/docker-maven-plugin   latest              ad1255a85bff        30 seconds ago      495MB
openjdk                            jre                 6b23d41384f9        5 months ago        479MB
```

## 详细配置

> 把这个插件配置贴到您项目 pom.xml 里, 使用 `mvn package` 命令打包项目就可以, 开箱即用

```xml
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.2.0</version>
    <configuration>
        <!--镜像名-->
        <imageName>${project.groupId}/${project.artifactId}</imageName>
        <!--基镜像-->
        <baseImage>openjdk:jre</baseImage>
        <!--程序入口-->
        <entryPoint>["java", "-jar", "/${project.build.finalName}.jar"]</entryPoint>
        <!--相关资源文件-->
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>build</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

其原理是用xml配置了一个 `Dockerfile`, 然后调用了本地 docker 去生成镜像

## 遇到的坑

因为当前项目是父子结构的项目, 并没有像普通 spring-boot 项目那样, 直接从 `spring-boot-starter-parent` 继承 pom, 所以会有些问题

- `[WARNING] 'build.plugins.plugin.version' for org.springframework.boot:spring-boot-maven-plugin is missing. @ line 50, column 21`

    这个警告的消除办法是给 `spring-boot-maven-plugin` 加上版本号, 例如本项目使用的 2.3.12.RELEASE 版本, 所以加上版本号 `2.3.12.RELEASE`,
    加上之后插件配置为

    ```xml
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.3.12.RELEASE</version>
    </plugin>
    ```

- 项目执行 `mvn package` 后, 启动程序时报错 "target/docker-maven-plugin-1.0.jar中没有主清单属性"

    这个问题来源于没有从父项目继承下来插件配置, 把 spring-boot 打包插件执行时间绑定到 maven 的 `package` 生命周期就好了,
    具体配置为加上 `executions` 节点, 完整配置为

    ```xml
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <version>2.3.12.RELEASE</version>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>repackage</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```

- 项目执行 `mvn package` 之后 `docker images` 没有出现打包的新镜像

    这个问题和  "target/docker-maven-plugin-1.0.jar中没有主清单属性" 问题一样, 把 docker-maven-plugin
    的 `build` 绑定到 `package` 生命周期就好了

    ```xml
    <plugin>
        <groupId>com.spotify</groupId>
        <artifactId>docker-maven-plugin</artifactId>
        <version>1.2.0</version>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>build</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ```

## 参考资料

- [maven 插件程序配置引导](http://ifeve.com/maven-guide-configuring-plugins/)
- [5、生命周期和插件介绍（maven笔记）](https://www.jianshu.com/p/403e0a40c966)
- [spotify/docker-maven-plugin](https://github.com/spotify/docker-maven-plugin)
- [docker run](https://docs.docker.com/engine/reference/commandline/run/)
