## 目录结构：
    - android               安卓工程
        - rlog              日志库
    - mysql                 数据库配置目录
        - my.conf           数据库配置文件
        - data              docker映射数据目录
        - source            数据库初始化sql文件
    - nginx                 nginx配置目录
        default.conf        nginx反向代理java web、web生产配置文件
    - web                   日志查看web端
        -.env               开发环境配置文件
        -.env.production    生产配置文件
    - .docker-compose.yml   项目编排配置文件
    - .docker-compose-prod.yml   部署编排配置文件

### Android API
#### RLogConfig.setIdentity(IIdentity)
设置用户唯一标识
#### RLogConfig.setHost(host)
设置服务器路径
#### RLogConfig.setTag(tag)
设置打印TAG
#### RLogConfig.setEncryptKey(byteArray)
设置密钥
#### RLogConfig.setPrinters(...IPrinter)
设置打印实现类
#### RLog.autoUpload()
开启日志自动上传
#### RLog.i(string)
打印日志，日志级别和Log一致
### 服务端接口
#### /log/getAllPackages: Response<List<String>>
获取所有应用包名
#### /log/getAllIdentity: Response<List<String>>
获取所有用户唯一标识
#### /log/getAllLevel: Response<List<String>>
获取日志等级
#### /log/getLogs: Response<List<LogBean>>
分页获取日志
#### /log/upload: Response<Void>
上传日志
### 开发
克隆本地后执行docker-compose up -d拉起项目所需环境容器(mysql:13306 -uroot -pcoolboy@88) 

### 部署
服务器克隆项目后执行docker-compose -f docker-compose-prod.yml up -d  
前端访问路径: log.host  
接口访问路径: api.host

日志内容查看mysql/source/init.sql