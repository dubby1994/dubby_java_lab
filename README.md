# Dubby的Java实验室

>记录平时学习Java的一些代码，关于的相关描述和解释可以在 [www.dubby.cn](www.dubby.cn) 上找到文章，这也是我的个人博客。

## achieve_timeout

实现超时控制。其中实现了两种常见的超时处理，一种是同步超时处理，目标方法需要可以相应线程的interrupt事件，另一种是异步超时处理，这种方式对目标方法没有要求，但是需要两个辅助线程来处理。

>了解Hystrix或者其他断路器的朋友应该都了解超时处理，那么具体是怎么实现的呢？

## multithreading_download

一个多线程下载工具，可以自定义下载的线程数

参考:[Java实现多线程下载文件](https://blog.dubby.cn/detail.html?id=9090)

## socks_proxy

基于Netty实现一个socks代理工具

## id_generator

雪花算法的一个实现

参考:[雪花算法(snowflake)](https://blog.dubby.cn/detail.html?id=9037)

## secret_tools

各种加密解密算法的使用

## qrcode

二维码生成

## have_a_try

其他测试尝试代码
