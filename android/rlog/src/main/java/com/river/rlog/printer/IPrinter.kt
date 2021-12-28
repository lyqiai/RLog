package com.river.rlog.printer

import com.river.rlog.LogBean

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
interface IPrinter {
    /**
     * 处理打印内容
     * @param logBean LogBean
     */
    fun printer(logBean: LogBean)
}