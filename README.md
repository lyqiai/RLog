## 目录结构：
    - android               安卓工程
        - rlog              日志库
    - mysql                 数据库配置目录
        - my.conf           数据库配置文件
        - data              docker映射数据目录
        - source            数据库初始化sql文件
    - nginx                 nginx配置目录
        default.conf        nginx反向代理java web、web生产配置文件
        develop.conf        开发nginx配置文件
    - web                   日志查看web端
        - src
            - LogApp.js     #11：const baseURL = "http://log.localhost/";需要配置服务器地址
    - .docker-compose.yml   项目编排配置文件
    - .docker-compose-develop.yml   开发编排配置文件

## 接入文档
### 部署
### 开发
### Android端接入
将rlog模块引入，在App中初始化即可
```kotlin
            RLogConfig
            .setIdentity(object: IIdentity{
                override fun identity(): String {
                    return "River"
                }
            })
            .setHost("http://api.localhost")
            .setEncryptKey("1234567890123456".toByteArray())
            .setPrinters(TerminalPrinter(), FilePrinter())
```