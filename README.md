## 平台简介

### 文档 http://doc.rycloud.zmrit.com 
### 启动演示视频 https://www.bilibili.com/video/av95044508/

### 源码
- [Github](https://github.com/zhangmrit/gy-platform)
- [Gitee](https://gitee.com/zhangmrit/gy-platform)

#### 友情链接 [若依/gy-platform](https://gitee.com/y_project/gy-platform) Element UI版本。
#### 分支说明

- **master** spring原生方式，使用eureka做注册中心和spring config做配置中心
- **nacos** 集成spring-cloud-alibaba 使用nacos做注册中心和配置中心

本项目FORK自  [若依/gy](https://gitee.com/y_project/gy)

蓝本是[zhangmrit/gy](https://gitee.com/zhangmrit/gy)

依次绑定host：

127.0.0.1 eureka7001.com

127.0.0.1 gateway.com

如果要使用eureka集群，请依次绑定eureka7002.com,eureka7003.com后修改各项目中的注释部分

```
gy-platform
|
├──gy-common --通用包
|  |
|  ├──gy-common-core --核心工具包
|  |
|  ├──gy-common-redis --redis工具包
|  |
|  ├──gy-common-log --日志工具包
|  |
|  ├──gy-common-auth --权限工具包
|
├──gy-config --cloud统一配置中心
|
├──gy-eureka --注册中心
|
├──gy-gateway --网关
|
├──gy-service-api --服务api模块
|  |
|  ├──gy-system-api --系统业务api
|
├──gy-service --微服务
|  |
|  ├──gy-system --系统业务
|  |
|  ├──gy-auth --授权中心
|  |
|  ├──gy-gen --代码生成
|  |
|  ├──gy-dfs --文件
|
├──gy-tool --工具
|  |
|  ├──gy-monitor --监控中心
|
├──gy-ant --前端 使用ant design框架

```



启动顺序：
- eureka
- config
- gateway
- system
- auth
- gen 可选
- dfs 可选

菜单sql有增加字段，以上传到sql文件
该分支是ant分支，前端使用ant-design-vue 项目地址 [gy-ant](https://gitee.com/zhangmrit/gy-ant)

monitor使用springadmin完成,目前只是最简单的用法,[详戳](http://doc.rycloud.zmrit.com/#/extra?id=%e7%9b%91%e6%8e%a7)

## gy Cloud交流群

QQ群：  [![加入QQ群](https://img.shields.io/badge/755109875-blue.svg)](https://jq.qq.com/?_wv=1027&k=5JGXHPD)  点击按钮入群。

### 鸣谢
- [AJ-Captcha](https://gitee.com/anji-plus/captcha) 因为使用场景的不同导致得拉代码修改，如果官方适配，可以删除本地代码

欢迎pr或者加入，给个star是最好的鞭策

##  请作者喝杯咖啡或者建设演示服务器


![](http://upload.ouliu.net/i/20191021180958e1ek5.png)