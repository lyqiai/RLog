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
    - develop.sh                    开发环境项目启动脚本
    - develop-restart.sh            开发环境服务端重启脚本,客户端热更新不用重启
    - prod.sh                       生产环境部署脚本

## 接入文档
### 部署
克隆项目到服务器，修改/web/src/LogApp.js中的baseURL后执行/prod.sh脚本即可。
### 开发
克隆项目后执行/develop.sh后项目即可启动，客户端由react编写，通过npm run start启动所以修改客户端文件有热更新效果，修改服务端文件后需要执行develop-restart.sh脚本重启java容器。
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