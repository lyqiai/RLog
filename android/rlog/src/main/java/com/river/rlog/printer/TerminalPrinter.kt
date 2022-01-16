package com.river.rlog.printer

import android.util.Log
import com.river.rlog.LogBean
import com.river.rlog.RLogConfig
import com.river.rlog.RLogType

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
class TerminalPrinter : IPrinter {
    override fun printer(logBean: LogBean) {
        val len = logBean.content.length
        val countOfSub = len / MAX_LEN
        if (countOfSub > 0) {
            val log = StringBuilder()
            var index = 0
            for (i in 0 until countOfSub) {
                log.append(logBean.content.substring(index, index + MAX_LEN))
                index += MAX_LEN
            }
            if (index != len) {
                log.append(logBean.content.substring(index, len))
            }
            Log.println(RLogType.getLevel(logBean.level), RLogConfig.tag, log.toString())
        } else {
            Log.println(RLogType.getLevel(logBean.level), RLogConfig.tag, logBean.content)
        }
    }

    companion object {
        private const val MAX_LEN = 256
    }
}