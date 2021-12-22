package com.river.rlog.printer

/**
 * @Author: River
 * @Emial: 1632958163@qq.com
 * @Create: 2021/11/9
 **/
interface IPrinter {
    /**
     * 处理打印内容
     * @param content String
     */
    fun printer(content: String)

    /**
     * 是否加密
     * @return Boolean
     */
    fun enableEncrypt(): Boolean = false
}