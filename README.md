# PanisBoot 后台管理系统

![SpringBoot](https://img.shields.io/badge/Spring%20Boot-3.3-blue.svg)
![JDK](https://img.shields.io/badge/JDK-21+-blue.svg)
![Version](https://img.shields.io/badge/Version-1.0.0--SNAPSHOT-blue.svg)
[![License](https://img.shields.io/badge/License-Apache%20License%202.0-B9D6AF.svg)](./LICENSE)
<br/>
[![Author](https://img.shields.io/badge/Author-paynezhuang-green.svg)](https://github.com/paynezhuang)
[![Copyright](https://img.shields.io/badge/Copyright-2024%20Zhuang%20Pan%20@PanisBoot-green.svg)](https://github.com/paynezhuang)

### 项目简介

Panis: 名为 Pan , is Pan 反过来 `Panis`，同译为：面包。

[`PanisBoot`](https://github.com/paynezhuang/panis-admin) 是一款现代化的后台管理系统脚手架，它基于 Spring Boot 3
框架进行开发。也得益于前端 [`@SoybeanAdmin 1.0.0`](https://github.com/soybeanjs/soybean-admin)
发版后，精致的用户界面和一致的编码，所以得此顺势完善此项目。
在市面上虽然存在众多出色的 Java 后端管理系统框架，但还是决定重复再造一个轮子。

### 技术选型

| 技术           | 说明        | 版本         |
|:-------------|:----------|:-----------|
| Spring Boot  | 核心框架      | 3.3.0      |
| MyBatis-Plus | 持久层框架     | 3.5.6      |
| MySQL        | 数据库       | 8.0.35     |
| Redis        | 缓存        | 7.2.3      |
| Sa-Token     | 鉴权框架      | 1.38.0     |
| Logback      | 日志管理      | 1.5.6      |
| Knife4j      | 接口文档      | 4.5.0      |
| Lombok       | 工具库       | 1.18.32    |
| Jackson      | JSON解析    | 2.15.4     |
| Gson         | JSON解析    | 2.11.0     |
| Guava        | Google工具库 | 33.2.0-jre |
| Hutool       | 工具库       | 5.8.28     |

### 项目源码

| 名称      | 链接                                                                      |
|:--------|:------------------------------------------------------------------------|
| 前端      | [Panis-admin](https://github.com/paynezhuang/panis-admin)               |
| 后端      | [Panis-boot](https://github.com/paynezhuang/panis-boot)                 |
| 后端扩展依赖库 | [Panis-boot-starter](https://github.com/paynezhuang/panis-boot-starter) |

### 项目启动

##### 前置环境

* **Java** 开发环境 >=JDK 21
* **Java** 开发工具 IDEA
* **Maven** 构建依赖环境 >=3.9.6
* **MySQL** 数据库 >=8.0.35
* **Redis** 缓存数据库 >=7.2.3

##### 克隆项目

```bash
git clone https://github.com/paynezhuang/panis-boot
git clone https://github.com/paynezhuang/panis-boot-starter
```

##### 导入启动

1. 将`panis-boot`以及`panis-boot-starter`分别导出到IDEA中，等待 Maven 依赖下载完成
2. 创建数据库`panis_boot`，导入`panis-boot-doc`项目中的`panis_boot.sql`文件
3. 修改`panis-boot`项目中的`application-dev.yml`文件中的`数据库`以及`Redis`连接信息
4. 启动`PanisBootApplication`类
5. 看到`---[PanisBoot]-[panis-boot-admin]-启动完成，当前使用的端口:[9999]，环境变量:[mybatis,dev]---`即代表启动成功

### 项目结构

```
PanisBoot
├── panis-boot-common -- 基础模块
├── panis-boot-admin -- 后台管理模块
│   └── controller  -- 控制层
├── panis-boot-infrastructure -- 基础配置
├── panis-boot-modules -- 业务模块
│   └── system 
│       └── repository -- 数据交互
│           └── mapper -- 持久层
│       └── domain  -- 业务模型
│           └── entity -- 数据库实体
│           └── vo -- 视图对象
│           └── bo -- 业务对象
│           └── dto -- 传输对象
│       └── service -- 服务层
│           └── impl -- 服务实现层
│       └── facade -- 门面层
│           └── impl -- 门面实现层
│   └── base -- 基础管理
│   └── ... -- 其他模块
└── pom.xml -- 公共依赖
```

#### `common` 和 `infrastructure` 区别

* `common`模块：通常包含通用的工具类、异常定义、常量定义等与业务无关的代码。这些代码可以被整个应用程序共享。
    - 通用工具类，比如日期处理、字符串处理等
    - 通用异常定义，比如业务异常、参数校验异常等
    - 通用常量定义，比如状态码、错误信息等

* `infrastructure`模块：通常包含与基础设施相关的代码，比如数据库访问、缓存、消息队列、配置管理等。这些代码通常是为了支持业务模块的运行而存在的。
    - 数据访问相关的代码，比如数据库连接、ORM框架配置、数据源配置等
    - 缓存相关的代码，比如缓存配置、缓存管理等
    - 消息队列相关的代码，比如消息生产者、消费者配置等
    - 配置管理相关的代码，比如配置加载、动态配置更新等

对于静态类、工具类、异常定义等，你可以根据其功能和作用来判断放入`common`还是`infrastructure`
模块。如果它们是通用的、与业务无关的，可以放入`common`模块；如果它们是为了支持业务模块的基础设施，可以放入`infrastructure`模块。

### 特别鸣谢

- [SoybeanJS](https://github.com/soybeanjs)
- [MyBatis-Plus](https://mybatis.plus/)
- [Sa-Token](https://sa-token.cc/)
- [Knife4j](https://doc.xiaominfo.com/)
- [HuTool](https://hutool.cn/)
- 不一一列举，感谢所有开源项目的贡献者

### 开源协议

项目基于 [Apache License 2.0 © 2024 Zhuang Pan](./LICENSE) 协议，仅供学习参考，商业使用请遵循作者版权信息，作者不保证也不承担任何软件的使用风险。