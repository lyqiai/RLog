package com.river.rlog.formatter

import com.river.rlog.LogBean
import com.river.rlog.RLogConfig

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class FileFormatter: ILogFormatter {
    override fun format(logBean: LogBean): String {
        return "{\"threadName\":\"${logBean.threadName}\",\"level\":\"${logBean.level}\",\"time\":\"${logBean.time}\",\"tag\":\"${RLogConfig.tag}\",\"content\":\"${logBean.content}\"}"
    }
}