# iniLoader4j


java 读取ini配置文件

##简介

git上没找到文档比较全的ini配置文件库，造个ini配置文件的轮子好了

##demo
```java
iniLoader loader=new iniLoader("./test.ini");//读一个ini文件  
iniProperties properties=loader.loadAllProperties();//load文件中所有内容  
String db=properties.getValue("COMMON", "DB");//获取一个值  
List<String> dbList=properties.getValues("COMMON", "DB");//获取所有值
```

##想法
 - 对小文件全部读进内存
 - 对大文件支持section索引和分section读进内存，采取多级缓存方案

##进度
 - 2016-10-29 完成了从配置文件中读section和key-value 还没写关于ini中注释的处理分支 

##结构

###package debugUtil
 - 提供debug时期的调试输出
 - 提供获取当前时间的方法
 - 提供日志记录

###package test
 - 用于测试
 - 懒，就不写单元测试了

###package iniLoader
 - 提供ini文件读入内存的load方法
 - 提供对外接口

###package fileUtil
 - 提供与ini文件配套的读方法
 - 提供与ini文件配套的写方法

