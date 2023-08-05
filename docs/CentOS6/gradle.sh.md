# 安装Gradle

## 一键安装

```bash
wget https://services.gradle.org/distributions/gradle-4.10.2-bin.zip
unzip  gradle-4.10.2-bin.zip
mv gradle-4.10.2 /usr/local/
ln -s /usr/local/gradle-4.10.2/bin/gradle /bin/gradle
gradle -v
```

如果没有unzip命令请安装unzip

```sh
yum -y install unzip
```

> **额外说明:** 如果您的项目使用 gradle 构建, 请查看下载安装 gradle 版本号与您项目 gradle 版本是否一致, 因为 gradle 升级的时候经常是不兼容升级(特别是最近的4.x升级到5.x), 所以容易出一些难以排查的 bug. 在没有验证兼容性问题前请勿轻易放到生产环境.

## 参考资料

- [Installing Zip and Unzip for Red Hat Enterprise Linux](https://www.luminanetworks.com/docs-lsc-610/Topics/SDN_Controller_Software_Installation_Guide/Appendix/Installing_Zip_and_Unzip_for_Red_Hat_Enterprise_Linux_1.html)
- [Gradle Docs - Installing manually](https://gradle.org/install/#manually)