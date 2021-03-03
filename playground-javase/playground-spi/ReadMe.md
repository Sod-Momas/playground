# playground-spi
Java SPI 机制的演示项目

## 使用方法

在 playground-spi-boot 项目中加入 
playground-spi-wxpay 的依赖则可以获取到 wxpay 的实现，
加入 playground-spi-alipay 的依赖则可以获取到 alipay 的实现，
两个都加入可以获取到两个实现。

## 原理

在 `resouces` 的 `META-INF/services` 目录下使用接口名字作为文件名，
文件内容为具体实现类的全类名，这样就可以通过 `java.util.ServiceLoader` 
来获取实现类，避免依赖了具体实现类导致的耦合问题