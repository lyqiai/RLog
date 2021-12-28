package com.river.rlog.formatter

import com.river.rlog.LogBean

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
interface ILogFormatter {
    fun format(logBean: LogBean): String
}