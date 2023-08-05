# Linux 安装java环境

## 一键安装

```bash
# x64 arch
wget -O jre-8u181-linux-x64.rpm http://javadl.oracle.com/webapps/download/AutoDL?BundleId=234463_96a7b8442fe848ef90c96a2fad6ed6d1
rpm -Uvh jre-8u181-linux-x64.rpm
java -version
```

## 详细解释

在服务器上我们一般不做java开发，只会用来运行java程序，所以安装jdk是没有必要的，只要安装一下JRE就足够了

最简单无脑的方式是先在有桌面环境的地方,通过浏览器点击下载好jre,然后再上传到Linux服务器安装

以下为详细步骤

1. 先找到下载jre的地方

    > 直接访问这个网址 `https://www.java.com/en/download/manual.jsp`

2. 下载jre,演示使用Red Hat系的CentOS系统,所以下载rpm包

    > 使用鼠标右键点击 `Linux x64 RPM` 获得64位Linux的rpm安装包,然后通过wget命令下载,其中-O表示下载到本地文件, `jre-8u181-linux-x64.rpm` 是保存的文件名

    ```sh
    wget -O jre-8u181-linux-x64.rpm http://javadl.oracle.com/webapps/download/AutoDL?BundleId=234463_96a7b8442fe848ef90c96a2fad6ed6d1
    ```

3. 安装jre，因为我下载的是rpm包，所以使用rpm安装，非redhat系的Linux请自行寻找对应 的安装包，用同样的方式下载并执行安装

    ```sh
    rpm -Uvh jre-8u171-linux-i586.rpm
    ```

4. 测试是否安装成功

    > 安装完成之后测试是否已经可以使用java

    ```sh
    java -version
    ```

    > 会输出 :

    ```output
    java version "1.8.0_171"
    Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
    Java HotSpot(TM) Client VM (build 25.171-b11, mixed mode, sharing)
    ```

## 参考

- [How do I download and install 32-bit Java for Linux?](https://www.java.com/en/download/help/linux_install.xml)
- [Download java for all opration system](https://www.java.com/en/download/manual.jsp)