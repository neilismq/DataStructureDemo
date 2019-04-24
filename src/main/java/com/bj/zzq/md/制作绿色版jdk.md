###制作绿色版jdk
在使用jdk时，对于不同应用要求的jdk版本可能有所不同，这时候需要一台电脑安装多版本的jdk，但官网windows系统目前只提供了`.exe`的安装包，并没有提供解压缩的安装包。安装`.exe`版本的jdk会自动在`Windows\System32`下产生多个java相关的exe程序，并且写入注册表与控制面板，导致切换jdk版本时需要修改注册表，比较麻烦。这里采用自己制作绿色版jdk，对以后的开发大有裨益。下面已jdk1.8为例子，其他版本同理。

1. **从官网下载jdk**

	1.8下载地址：<https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
2. **解压缩**

